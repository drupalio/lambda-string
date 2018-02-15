package fr.pturpin.lambdaString;

import org.objectweb.asm.*;

import java.util.Objects;
import java.util.function.Consumer;

public final class MetaMethodVisitor extends MethodVisitor {

  private static final String NAME_METHOD_VISITOR = "jdk/internal/org/objectweb/asm/MethodVisitor";

  public MetaMethodVisitor(int api, MethodVisitor mv) {
    super(api, Objects.requireNonNull(mv));
  }

  @Override
  public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
    throw new UnsupportedOperationException();
  }

  @Override
  public AnnotationVisitor visitAnnotationDefault() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void visitAttribute(Attribute attribute) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void visitCode() {
    dup();
    invoke("visitCode", "()V");
  }

  @Override
  public void visitEnd() {
    dup();
    invoke("visitEnd", "()V");
  }

  @Override
  public void visitFieldInsn(int opcode, String owner, String name, String desc) {
    dup();
    push(opcode);
    push(owner);
    push(name);
    push(desc);
    invoke("visitFieldInsn", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
  }

  @Override
  public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
    dup();
    push(type);
    push(nLocal);
    push(local, "java/local/Object");
    push(nStack);
    push(stack, "java/local/Object");
    invoke("visitFrame", "(II[Ljava/lang/Object;I[Ljava/lang/Object;)V");
  }

  @Override
  public void visitIincInsn(int var, int increment) {
    dup();
    push(var);
    push(increment);
    invoke("visitIincInsn", "(I)V");
  }

  @Override
  public void visitInsn(int opcode) {
    dup();
    push(opcode);
    invoke("visitInsn", "(I)V");
  }

  @Override
  public AnnotationVisitor visitInsnAnnotation(int i, TypePath typePath, String s, boolean b) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void visitIntInsn(int opcode, int operand) {
    dup();
    push(opcode);
    push(operand);
    invoke("visitIntInsn", "(II)V");
  }

  @Override
  public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
    dup();
    push(name);
    push(desc);
    push(bsm);
    push(bsmArgs, "java/lang/Object");
    invoke("visitInvokeDynamicInsn",
        "(Ljava/lang/String;Ljava/lang/String;" +
            "Ljdk/internal/org/objectweb/asm/Handle;" +
            "[Ljava/lang/Object;)V");
  }

  @Override
  public void visitJumpInsn(int opcode, Label label) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void visitLabel(Label label) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void visitLdcInsn(Object cst) {
    dup();
    push(cst);
    invoke("visitLdcInsn", "(Ljava/lang/Object;)V");
  }

  @Override
  public void visitLineNumber(int line, Label label) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
    throw new UnsupportedOperationException();
  }

  @Override
  public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String desc, boolean visible) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void visitMaxs(int maxStack, int maxLocals) {
    dup();
    push(maxStack);
    push(maxLocals);
    invoke("visitMaxs", "(II)V");
  }

  @Override
  public void visitMethodInsn(int opcode, String owner, String name, String desc) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
    dup();
    push(opcode);
    push(owner);
    push(name);
    push(desc);
    push(itf);
    invoke("visitMethodInsn", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V");
  }

  @Override
  public void visitMultiANewArrayInsn(String desc, int dims) {
    dup();
    push(desc);
    push(dims);
    invoke("visitMultiANewArrayInsn", "(Ljava/lang/String;I)V");
  }

  @Override
  public void visitParameter(String name, int access) {
    dup();
    push(name);
    push(access);
    invoke("visitParameter", "(Ljava/lang/String;I)V");
  }

  @Override
  public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
    throw new UnsupportedOperationException();
  }

  @Override
  public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
    throw new UnsupportedOperationException();
  }

  public void visitTryCatchBlock(Consumer<MetaMethodVisitor> tryBlock, Consumer<MetaMethodVisitor> endTryBlock, Consumer<MetaMethodVisitor> catchBlock, String type) {
    mv.visitTypeInsn(Opcodes.NEW, "jdk/internal/org/objectweb/asm/Label");
    mv.visitInsn(Opcodes.DUP);
    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "jdk/internal/org/objectweb/asm/Label", "<init>", "()V", false);
    mv.visitVarInsn(Opcodes.ASTORE, 1);

    mv.visitTypeInsn(Opcodes.NEW, "jdk/internal/org/objectweb/asm/Label");
    mv.visitInsn(Opcodes.DUP);
    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "jdk/internal/org/objectweb/asm/Label", "<init>", "()V", false);
    mv.visitVarInsn(Opcodes.ASTORE, 2);

    mv.visitTypeInsn(Opcodes.NEW, "jdk/internal/org/objectweb/asm/Label");
    mv.visitInsn(Opcodes.DUP);
    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "jdk/internal/org/objectweb/asm/Label", "<init>", "()V", false);
    mv.visitVarInsn(Opcodes.ASTORE, 3);

    mv.visitVarInsn(Opcodes.ALOAD, 1);
    mv.visitVarInsn(Opcodes.ALOAD, 2);
    mv.visitVarInsn(Opcodes.ALOAD, 3);
    push(type);
    invoke("visitTryCatchBlock",
        "(Ljdk/internal/org/objectweb/asm/Label;" +
            "Ljdk/internal/org/objectweb/asm/Label;" +
            "Ljdk/internal/org/objectweb/asm/Label;" +
            "Ljava/lang/String;)V");

    dup();
    mv.visitVarInsn(Opcodes.ALOAD, 1);
    invoke("visitLabel", "(Ljdk/internal/org/objectweb/asm/Label;)V");

    tryBlock.accept(this);

    dup();
    mv.visitVarInsn(Opcodes.ALOAD, 2);
    invoke("visitLabel", "(Ljdk/internal/org/objectweb/asm/Label;)V");

    endTryBlock.accept(this);

    dup();
    mv.visitVarInsn(Opcodes.ALOAD, 3);
    invoke("visitLabel", "(Ljdk/internal/org/objectweb/asm/Label;)V");

    // stack
    push(Opcodes.F_SAME1);
    push(0);
    push(null);
    push(1);
    push(new Object[] { type }, "java/lang/Object");
    invoke("visitFrame", "(I[Ljava/lang/Object;I[Ljava/lang/Object;)V");

    catchBlock.accept(this);
  }

  @Override
  public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void visitTypeInsn(int opcode, String type) {
    dup();
    push(opcode);
    push(type);
    invoke("visitTypeInsn", "(ILjava/lang/String;)V");
  }

  @Override
  public void visitVarInsn(int opcode, int var) {
    dup();
    push(opcode);
    push(var);
    invoke("visitVarInsn", "(II)V");
  }

  private void invoke(String name, String desc) {
    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, NAME_METHOD_VISITOR, name, desc, false);
  }

  private void dup() {
    mv.visitInsn(Opcodes.DUP);
  }

  private void push(boolean bool) {
    push(bool ? 1 : 0);
  }

  private void push(int n) {
    if (n >= -1 && n <= 5) {
      mv.visitInsn(Opcodes.ICONST_0 + n);
    } else if (n >= Byte.MIN_VALUE && n <= Byte.MAX_VALUE) {
      mv.visitIntInsn(Opcodes.BIPUSH, n);
    } else if (n >= Short.MIN_VALUE && n <= Short.MAX_VALUE) {
      mv.visitIntInsn(Opcodes.SIPUSH, n);
    } else {
      mv.visitLdcInsn(n);
    }
  }

  private void push(Object cst) {
    if (cst == null) {
      mv.visitInsn(Opcodes.ACONST_NULL);
    } else {
      mv.visitLdcInsn(cst);
    }
  }

  private void push(Object[] array, String name) {
    if (array == null) {
      mv.visitInsn(Opcodes.ACONST_NULL);
    } else {
      push(array.length);
      mv.visitTypeInsn(Opcodes.ANEWARRAY, name);
      for (int i = 0; i < array.length; i++) {
        Object cst = array[i];
        dup();
        push(i);
        push(cst);
        mv.visitInsn(Opcodes.AASTORE);
      }
    }
  }

}