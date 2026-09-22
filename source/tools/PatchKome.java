import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.jar.*;
import jdk.internal.org.objectweb.asm.*;
import jdk.internal.org.objectweb.asm.tree.*;

/** Small verified hooks; original obfuscated implementation is retained verbatim. */
public class PatchKome implements Opcodes {
    static final Set<String> PATCHED=new HashSet<>(Arrays.asList("Game","ay","A","aw","bp","aZ","aV","r","q","m"));
    static void replace(MethodNode method,InsnList code) {
        method.instructions.clear();method.tryCatchBlocks.clear();
        if(method.localVariables!=null)method.localVariables.clear();
        method.instructions.add(code);
    }
    static void range(MethodNode method,AbstractInsnNode first,AbstractInsnNode last,InsnList replacement) {
        method.instructions.insertBefore(first,replacement);
        AbstractInsnNode after=last.getNext();
        while(first!=after){AbstractInsnNode next=first.getNext();method.instructions.remove(first);first=next;}
    }
    static InsnList gameCall(String method) {
        InsnList code = new InsnList();
        code.add(new VarInsnNode(ALOAD, 0));
        code.add(new MethodInsnNode(INVOKESTATIC,"KomeSupport",method,"(LGame;)V",false));
        return code;
    }
    static byte[] patch(String name, byte[] bytes) {
        ClassNode type = new ClassNode();
        new ClassReader(bytes).accept(type, 0);
        int hooks = 0;
        for(FieldNode field:type.fields)if((name.equals("bp")&&(field.name.equals("a")||field.name.equals("d")))||((name.equals("aZ")||name.equals("aV"))&&(field.name.equals("a")||field.name.equals("c"))))field.access|=ACC_VOLATILE;
        for (MethodNode method : type.methods) {
            if(name.equals("bp")) {
                String helper=method.desc.equals("()V")?(method.name.equals("c")?"pump":method.name.equals("i")?"send":method.name.equals("b")?"drain":null):
                    method.name.equals("d")&&method.desc.equals("()Ljava/lang/String;")?"read":
                    method.name.equals("a")&&method.desc.equals("(Z)V")?"disconnect":null;
                if(helper!=null){
                    InsnList code=new InsnList();code.add(new VarInsnNode(ALOAD,0));code.add(new VarInsnNode(ALOAD,0));code.add(new FieldInsnNode(GETFIELD,"bp","k","LGame;"));
                    if(helper.equals("disconnect"))code.add(new VarInsnNode(ILOAD,1));
                    String result=helper.equals("read")?"Ljava/lang/String;":"V";
                    code.add(new MethodInsnNode(INVOKESTATIC,"KomeTransport",helper,"(Lbp;LGame;"+(helper.equals("disconnect")?"Z":"")+")"+result,false));
                    code.add(new InsnNode(helper.equals("read")?ARETURN:RETURN));replace(method,code);hooks++;continue;
                }
            }
            if((name.equals("r")&&method.name.equals("keyTyped"))||(name.equals("q")&&method.name.equals("actionPerformed"))) {
                InsnList code=new InsnList();code.add(new VarInsnNode(ALOAD,0));code.add(new FieldInsnNode(GETFIELD,name,"a","Lm;"));
                if(name.equals("r"))code.add(new VarInsnNode(ALOAD,1));
                code.add(new MethodInsnNode(INVOKESTATIC,"KomeUi",name.equals("r")?"keyTyped":"typingStopped",name.equals("r")?"(Lm;Ljava/awt/event/KeyEvent;)V":"(Lm;)V",false));
                code.add(new InsnNode(RETURN));replace(method,code);hooks++;continue;
            }
            if(name.equals("m")&&method.desc.equals("(Ljava/lang/String;)V")&&Arrays.asList("a","b","c").contains(method.name)) {
                for(AbstractInsnNode node:method.instructions.toArray())if(node instanceof FieldInsnNode&&node.getOpcode()==GETFIELD&&((FieldInsnNode)node).owner.equals("m")&&((FieldInsnNode)node).name.equals("e"))method.instructions.set(node,new MethodInsnNode(INVOKESTATIC,"KomeUi","muted","(Lm;)Z",false));
                InsnList code=new InsnList();LabelNode proceed=new LabelNode();code.add(new VarInsnNode(ALOAD,0));code.add(new VarInsnNode(ALOAD,1));
                code.add(new InsnNode(method.name.equals("a")?ICONST_0:method.name.equals("b")?ICONST_1:ICONST_2));
                code.add(new MethodInsnNode(INVOKESTATIC,"KomeUi","message","(Lm;Ljava/lang/String;I)Z",false));code.add(new JumpInsnNode(IFEQ,proceed));code.add(new InsnNode(RETURN));code.add(proceed);method.instructions.insert(code);hooks++;
            }
            if(name.equals("m")&&method.name.equals("actionPerformed"))for(AbstractInsnNode node:method.instructions.toArray())if(node instanceof MethodInsnNode&&((MethodInsnNode)node).owner.equals("java/io/PrintStream")&&((MethodInsnNode)node).name.equals("println")){
                InsnList context=new InsnList();context.add(new VarInsnNode(ALOAD,0));context.add(new FieldInsnNode(GETFIELD,"m","d","LGame;"));context.add(new FieldInsnNode(GETFIELD,"Game","z","Lbp;"));method.instructions.insertBefore(node,context);
                method.instructions.set(node,new MethodInsnNode(INVOKESTATIC,"KomeUi","notice","(Ljava/io/PrintStream;Ljava/lang/String;Lbp;)V",false));hooks++;
            }
            if(name.equals("Game")&&method.name.equals("c")&&method.desc.equals("()V")){
                InsnList code=new InsnList();LabelNode proceed=new LabelNode();code.add(new VarInsnNode(ALOAD,0));code.add(new MethodInsnNode(INVOKESTATIC,"KomeUi","refresh","(LGame;)Z",false));code.add(new JumpInsnNode(IFEQ,proceed));code.add(new InsnNode(RETURN));code.add(proceed);method.instructions.insert(code);hooks++;
            }
            if((name.equals("aZ")||name.equals("aV"))&&method.name.equals("a")&&(method.access&ACC_STATIC)==0) {
                method.access|=ACC_SYNCHRONIZED;
                boolean start=method.desc.startsWith("(II");
                if(start){
                    AbstractInsnNode first=null,last=null;
                    for(AbstractInsnNode node:method.instructions.toArray()){
                        if(node instanceof TypeInsnNode&&node.getOpcode()==NEW&&((TypeInsnNode)node).desc.equals("javax/swing/JDialog"))first=node.getPrevious();
                        if(node instanceof MethodInsnNode&&((MethodInsnNode)node).owner.equals("java/lang/Thread")&&((MethodInsnNode)node).name.equals("start"))last=node;
                    }
                    if(first==null||last==null)throw new IllegalStateException("Secure dialog creation changed: "+name);
                    InsnList code=new InsnList();code.add(new VarInsnNode(ALOAD,0));code.add(new VarInsnNode(ALOAD,0));code.add(new FieldInsnNode(GETFIELD,name,"b","LGame;"));
                    code.add(new LdcInsnNode(name.equals("aZ")?"SecureDiceRoller.0":"Opponent requested a safe dice roll"));
                    if(name.equals("aZ"))code.add(new MethodInsnNode(INVOKESTATIC,"d","b","(Ljava/lang/String;)Ljava/lang/String;",false));
                    code.add(new MethodInsnNode(INVOKESTATIC,"KomeRollUi","open","(Ljava/lang/Object;LGame;Ljava/lang/String;)V",false));range(method,first,last,code);hooks++;
                    if(name.equals("aV"))for(AbstractInsnNode node:method.instructions.toArray())if(node instanceof FieldInsnNode&&node.getOpcode()==PUTFIELD&&((FieldInsnNode)node).name.equals("c")&&node.getPrevious().getOpcode()==ICONST_0){
                        range(method,node.getPrevious().getPrevious(),node,new InsnList());hooks++;
                    }
                }else{
                    AbstractInsnNode first=null,last=null;
                    for(AbstractInsnNode node:method.instructions.toArray()){
                        if(first==null&&node instanceof FieldInsnNode&&node.getOpcode()==GETFIELD&&((FieldInsnNode)node).name.equals("a"))first=node.getPrevious();
                        if(node instanceof MethodInsnNode&&((MethodInsnNode)node).owner.equals("javax/swing/JDialog")&&((MethodInsnNode)node).name.equals("dispose"))last=node;
                    }
                    if(first==null||last==null)throw new IllegalStateException("Secure dialog cleanup changed: "+name);
                    InsnList code=new InsnList();code.add(new VarInsnNode(ALOAD,0));code.add(new MethodInsnNode(INVOKESTATIC,"KomeRollUi","close","(Ljava/lang/Object;)V",false));range(method,first,last,code);hooks++;
                }
                for(AbstractInsnNode node:method.instructions.toArray())if(node instanceof MethodInsnNode&&((MethodInsnNode)node).owner.equals("javax/swing/JOptionPane")&&((MethodInsnNode)node).name.equals("showMessageDialog"))method.instructions.set(node,new MethodInsnNode(INVOKESTATIC,"KomeUi","alert","(Ljava/awt/Component;Ljava/lang/Object;)V",false));
            }
            if(name.equals("bp") && method.name.equals("run") && method.desc.equals("()V")) {
                int matched=0;
                for(AbstractInsnNode node:method.instructions.toArray())if(node instanceof FieldInsnNode && node.getOpcode()==PUTFIELD && ((FieldInsnNode)node).owner.equals("bp") && ((FieldInsnNode)node).name.equals("d")) {
                    InsnList code=new InsnList();LabelNode accepted=new LabelNode();
                    code.add(new VarInsnNode(ALOAD,0));code.add(new FieldInsnNode(GETFIELD,"bp","k","LGame;"));
                    code.add(new MethodInsnNode(INVOKESTATIC,"KomeVersion","verify","(LGame;)Z",false));
                    code.add(new JumpInsnNode(IFNE,accepted));code.add(new InsnNode(RETURN));code.add(accepted);
                    method.instructions.insert(node,code);matched++;hooks++;
                }
                if(matched!=2)throw new IllegalStateException("Connection setup changed: "+matched);
            }
            if(name.equals("A") && method.name.equals("actionPerformed") && method.desc.equals("(Ljava/awt/event/ActionEvent;)V")) {
                InsnList code=new InsnList();LabelNode next=new LabelNode();
                code.add(new VarInsnNode(ALOAD,0));code.add(new FieldInsnNode(GETFIELD,"A","b","LGame;"));code.add(new VarInsnNode(ALOAD,1));
                code.add(new MethodInsnNode(INVOKESTATIC,"KomeCombat","manualReveal","(LGame;Ljava/awt/event/ActionEvent;)Z",false));
                code.add(new JumpInsnNode(IFEQ,next));code.add(new InsnNode(RETURN));code.add(next);method.instructions.insert(code);hooks++;
            }
            if(name.equals("ay") && method.name.equals("l") && method.desc.equals("(Ljava/lang/String;Ljava/lang/String;)V")) {
                int matched=0;
                for(AbstractInsnNode node:method.instructions.toArray())if(node instanceof MethodInsnNode) {
                    MethodInsnNode call=(MethodInsnNode)node;
                    if(call.owner.equals("A") && call.name.equals("actionPerformed") && call.desc.equals("(Ljava/awt/event/ActionEvent;)V")) {
                        InsnList context=new InsnList();context.add(new VarInsnNode(ALOAD,0));context.add(new FieldInsnNode(GETFIELD,"ay","a","LGame;"));
                        method.instructions.insertBefore(node,context);
                        method.instructions.set(node,new MethodInsnNode(INVOKESTATIC,"KomeCombat","automaticReveal","(LA;Ljava/awt/event/ActionEvent;LGame;)V",false));matched++;hooks++;
                    }
                }
                if(matched!=1)throw new IllegalStateException("Combat reveal call changed: "+matched);
            }
            if(name.equals("A") && method.name.equals("J") && method.desc.equals("()V")) {
                int matched=0;
                for(AbstractInsnNode node:method.instructions.toArray())if(node instanceof TypeInsnNode && node.getOpcode()==INSTANCEOF && ((TypeInsnNode)node).desc.equals("I")) {
                    method.instructions.set(node,new MethodInsnNode(INVOKESTATIC,"KomeDie","fellowshipDie","(Laq;)Z",false));matched++;hooks++;
                }
                if(matched!=1)throw new IllegalStateException("Fellowship die check changed: "+matched);
            }
            if(name.equals("A") && method.name.equals("k") && method.desc.equals("()V") || name.equals("ay") && method.name.equals("r") && method.desc.equals("(Ljava/lang/String;Lbb;)V")) {
                boolean allocation=false;int matched=0;
                for(AbstractInsnNode node:method.instructions.toArray()) {
                    if(node instanceof LdcInsnNode && "Controls.350".equals(((LdcInsnNode)node).cst))allocation=true;
                    if(allocation && node instanceof MethodInsnNode) {
                        MethodInsnNode call=(MethodInsnNode)node;
                        if(call.owner.equals("d") && call.name.equals("k") && call.desc.equals("()Ljava/lang/String;")) {
                            method.instructions.set(node,new MethodInsnNode(INVOKESTATIC,"KomeDie","automaticEyes","(Ld;)Ljava/lang/String;",false));allocation=false;matched++;hooks++;
                        }
                    }
                }
                if(matched!=1)throw new IllegalStateException("Automatic Eye allocation changed: "+matched);
            }
            if(name.equals("aw")) {
                InsnList code=new InsnList();LabelNode next=new LabelNode();
                if(method.name.equals("paint") && method.desc.equals("(Ljava/awt/Graphics;)V")) {
                    code.add(new VarInsnNode(ALOAD,0));code.add(new FieldInsnNode(GETFIELD,"aw","e","LGame;"));
                    code.add(new VarInsnNode(ALOAD,0));code.add(new VarInsnNode(ALOAD,1));
                    code.add(new MethodInsnNode(INVOKESTATIC,"KomeHuntPool","paint","(LGame;Ljavax/swing/JComponent;Ljava/awt/Graphics;)Z",false));
                    code.add(new JumpInsnNode(IFEQ,next));code.add(new InsnNode(RETURN));code.add(next);
                    method.instructions.insert(code);hooks++;
                }
                if(method.name.equals("mouseClicked") && method.desc.equals("(Ljava/awt/event/MouseEvent;)V")) {
                    code.add(new VarInsnNode(ALOAD,0));code.add(new FieldInsnNode(GETFIELD,"aw","e","LGame;"));
                    code.add(new MethodInsnNode(INVOKESTATIC,"KomeHuntPool","blockSelection","(LGame;)Z",false));
                    code.add(new JumpInsnNode(IFEQ,next));code.add(new InsnNode(RETURN));code.add(next);
                    method.instructions.insert(code);hooks++;
                }
                if(method.name.equals("a") && method.desc.equals("(I)Ljava/awt/Dimension;")) {
                    code.add(new VarInsnNode(ALOAD,0));code.add(new FieldInsnNode(GETFIELD,"aw","e","LGame;"));
                    code.add(new MethodInsnNode(INVOKESTATIC,"KomeHuntPool","blockSelection","(LGame;)Z",false));
                    code.add(new JumpInsnNode(IFEQ,next));
                    code.add(new VarInsnNode(ALOAD,0));code.add(new FieldInsnNode(GETFIELD,"aw","e","LGame;"));
                    code.add(new MethodInsnNode(INVOKESTATIC,"KomeHuntPool","size","(LGame;)Ljava/awt/Dimension;",false));
                    code.add(new InsnNode(ARETURN));code.add(next);method.instructions.insert(code);hooks++;
                }
            }
            if (name.equals("Game")) {
                if (method.name.equals("<init>") || method.name.equals("K")) {
                    for (AbstractInsnNode node : method.instructions.toArray()) {
                        if (node instanceof FieldInsnNode && node.getOpcode() == PUTSTATIC && ((FieldInsnNode)node).owner.equals("Game") && ((FieldInsnNode)node).name.equals("h")) {
                            method.instructions.insert(node,gameCall("preferences")); hooks++;
                        }
                    }
                }
                if (method.name.equals("Z") && method.desc.equals("()V")) {
                    method.instructions.insert(gameCall("prepare")); hooks++;
                }
                if((method.name.equals("o") || method.name.equals("r")) && method.desc.equals("()V")) {
                    // The original seat-release code accesses WoME hands even in base/LoME.
                    // Skip only those optional regions when the current game lacks them.
                    for(AbstractInsnNode node:method.instructions.toArray()) {
                        if(!(node instanceof FieldInsnNode) || node.getOpcode()!=GETSTATIC || !((FieldInsnNode)node).owner.equals("Game") || !((FieldInsnNode)node).name.equals("p"))continue;
                        AbstractInsnNode index=node.getNext();
                        if(!(index instanceof IntInsnNode))continue;
                        int region=((IntInsnNode)index).operand;if(region!=202 && region!=203)continue;
                        AbstractInsnNode call=index.getNext().getNext().getNext();
                        if(!(call instanceof MethodInsnNode) || !((MethodInsnNode)call).desc.equals("(Z)V"))throw new IllegalStateException("Unexpected seat-release bytecode");
                        LabelNode after=new LabelNode();InsnList guard=new InsnList();
                        guard.add(new FieldInsnNode(GETSTATIC,"Game","p","[Ld;"));guard.add(new InsnNode(ARRAYLENGTH));
                        guard.add(new IntInsnNode(SIPUSH,region));guard.add(new JumpInsnNode(IF_ICMPLE,after));
                        method.instructions.insertBefore(node,guard);method.instructions.insert(call,after);hooks++;
                    }
                }
                String end = method.name.equals("<init>") ? "ready" : method.name.equals("W") ? "reset" : method.name.equals("b") && method.desc.equals("()V") ? "title" : null;
                if (end != null) for (AbstractInsnNode node : method.instructions.toArray()) {
                    if (node.getOpcode() == RETURN) {
                        method.instructions.insertBefore(node,gameCall(end)); hooks++;
                        if(end.equals("ready")||end.equals("reset")) {
                            InsnList combat=new InsnList();combat.add(new VarInsnNode(ALOAD,0));
                            combat.add(new MethodInsnNode(INVOKESTATIC,"KomeCombat",end.equals("ready")?"install":"reset","(LGame;)V",false));
                            method.instructions.insertBefore(node,combat);hooks++;
                        }
                    }
                }
            }
            if(name.equals("A") && method.name.equals("q") && method.desc.equals("()V")) {
                // Reject a mismatched save BEFORE native code clears the log and seats.
                int fileLocal=-1;
                for(AbstractInsnNode node:method.instructions.toArray()) {
                    if(node instanceof MethodInsnNode && ((MethodInsnNode)node).name.equals("getSelectedFile")) {
                        if(node.getNext() instanceof VarInsnNode && node.getNext().getOpcode()==ASTORE)fileLocal=((VarInsnNode)node.getNext()).var;
                    }
                    if(node instanceof FieldInsnNode && node.getOpcode()==PUTFIELD && ((FieldInsnNode)node).owner.equals("Game") && ((FieldInsnNode)node).name.equals("Z") && node.getPrevious().getOpcode()==ICONST_1) {
                        AbstractInsnNode before=node.getPrevious().getPrevious().getPrevious();
                        if(fileLocal<0 || before.getOpcode()!=ALOAD)throw new IllegalStateException("Unexpected save loader bytecode");
                        InsnList code=new InsnList();LabelNode next=new LabelNode();
                        code.add(new VarInsnNode(ALOAD,0));code.add(new FieldInsnNode(GETFIELD,"A","b","LGame;"));
                        code.add(new VarInsnNode(ALOAD,fileLocal));
                        code.add(new MethodInsnNode(INVOKESTATIC,"KomeSupport","allowLoad","(LGame;Ljava/io/File;)Z",false));
                        code.add(new JumpInsnNode(IFNE,next));code.add(new InsnNode(RETURN));code.add(next);
                        method.instructions.insertBefore(before,code);hooks++;break;
                    }
                }
            }
            if (name.equals("ay") && method.name.equals("c") && method.desc.equals("(Ljava/lang/String;)V")) {
                for (AbstractInsnNode node : method.instructions.toArray()) {
                    if (!(node instanceof MethodInsnNode)) continue;
                    MethodInsnNode call = (MethodInsnNode) node;
                    if (call.owner.equals("ay") && call.name.equals("d") && call.desc.equals("(Ljava/lang/String;)V")) {
                        LabelNode next = new LabelNode();
                        InsnList code = new InsnList();
                        code.add(new VarInsnNode(ALOAD,0));
                        code.add(new FieldInsnNode(GETFIELD,"ay","a","LGame;"));
                        code.add(new VarInsnNode(ALOAD,1));
                        code.add(new MethodInsnNode(INVOKESTATIC,"KomeCombat","command","(LGame;Ljava/lang/String;)Z",false));
                        code.add(new JumpInsnNode(IFEQ,next));
                        code.add(new InsnNode(RETURN)); code.add(next);
                        method.instructions.insert(node,code); hooks++;
                    }
                }
            }
            if(name.equals("ay") && method.name.equals("a") && method.desc.equals("(Ljava/io/File;)V")) {
                InsnList code=new InsnList();LabelNode next=new LabelNode();
                code.add(new VarInsnNode(ALOAD,0));code.add(new FieldInsnNode(GETFIELD,"ay","a","LGame;"));
                code.add(new VarInsnNode(ALOAD,1));code.add(new MethodInsnNode(INVOKESTATIC,"KomeSupport","allowLoad","(LGame;Ljava/io/File;)Z",false));
                code.add(new JumpInsnNode(IFNE,next));code.add(new InsnNode(RETURN));code.add(next);
                method.instructions.insert(code);hooks++;
            }
        }
        if (hooks == 0) throw new IllegalStateException("No hooks found in " + name);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS) {
            protected ClassLoader getClassLoader() { return PatchKome.class.getClassLoader(); }
        };
        type.accept(writer);
        System.out.println(name + ": " + hooks + " hooks");
        return writer.toByteArray();
    }
    public static void main(String[] args) throws Exception {
        Path source=Paths.get(args[0]), classes=Paths.get(args[1]), output=Paths.get(args[2]);
        try (JarFile original=new JarFile(source.toFile()); JarOutputStream dest=new JarOutputStream(Files.newOutputStream(output))) {
            Set<String> names=new HashSet<>();
            Enumeration<JarEntry> entries=original.entries();
            while(entries.hasMoreElements()) {
                JarEntry entry=entries.nextElement(); String name=entry.getName();
                byte[] bytes=original.getInputStream(entry).readAllBytes();
                if(name.endsWith(".class")&&PATCHED.contains(name.substring(0,name.length()-6))) bytes=patch(name.substring(0,name.length()-6),bytes);
                dest.putNextEntry(new JarEntry(name)); dest.write(bytes); dest.closeEntry(); names.add(name);
            }
            try (java.util.stream.Stream<Path> paths=Files.walk(classes)) {
                for(Path path:(Iterable<Path>)paths.filter(p->p.toString().endsWith(".class"))::iterator) {
                    String name=classes.relativize(path).toString().replace('\\','/');
                    if(!names.add(name)) throw new IllegalStateException("Unexpected replacement: "+name);
                    dest.putNextEntry(new JarEntry(name));dest.write(Files.readAllBytes(path));dest.closeEntry();
                }
            }
        }
    }
}
