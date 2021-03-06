package fr.pturpin.lambdastring.transform;

import fr.pturpin.lambdastring.asm.FetchingFirstLineNumberOfIndyClassVisitor;
import fr.pturpin.lambdastring.strategy.LambdaToStringException;
import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.OptionalInt;

import static java.util.Objects.requireNonNull;

public final class LambdaMetaInfo {

    private final Class<?> targetClass;
    private final Class<?> declaringClass;
    private final String methodName;
    private final String methodDesc;
    private final int referenceKind;
    private final int modifers;
    private int declarationLine;
    private volatile boolean isDeclarationLineComputed;

    public LambdaMetaInfo(
            Class<?> targetClass,
            Class<?> declaringClass,
            String methodName,
            String methodDesc,
            int referenceKind,
            int modifers) {
        this.targetClass = requireNonNull(targetClass);
        this.declaringClass = requireNonNull(declaringClass);
        this.methodName = requireNonNull(methodName);
        this.methodDesc = requireNonNull(methodDesc);
        this.referenceKind = referenceKind;
        this.modifers = modifers;
        this.declarationLine = -1;
        this.isDeclarationLineComputed = false;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public int getReferenceKind() {
        return referenceKind;
    }

    public int getModifers() {
        return modifers;
    }

    public Class<?> getDeclaringClass() {
        return declaringClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getMethodDesc() {
        return methodDesc;
    }

    public OptionalInt getDeclarationLine() throws LambdaToStringException {
        // Not really important to really sync the check. The output is expected to be constant and the worst case
        // just computing this constant multiple times.
        if (!isDeclarationLineComputed) {
            isDeclarationLineComputed = true;
            declarationLine = computeDeclarationLine();
        }
        return declarationLine == -1 ? OptionalInt.empty() : OptionalInt.of(declarationLine);
    }

    private int computeDeclarationLine() throws LambdaToStringException {
        ClassReader cr;
        ClassLoader classLoader = declaringClass.getClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        String resourceName = declaringClass.getName().replace('.', '/') + ".class";
        try (InputStream classStream = classLoader.getResourceAsStream(resourceName)) {
            if (classStream == null) {
                throw new LambdaToStringException("Could not find resource " + resourceName);
            }
            cr = new ClassReader(classStream);
        } catch (IOException e) {
            throw new LambdaToStringException("Could not read class " + declaringClass, e);
        }

        int[] line = new int[]{ -1 };
        FetchingFirstLineNumberOfIndyClassVisitor visitor = new FetchingFirstLineNumberOfIndyClassVisitor(
                methodName,
                methodDesc,
                foundLine -> line[0] = foundLine);
        cr.accept(visitor, ClassReader.SKIP_FRAMES);
        return line[0];
    }

}
