"""Build the Java 8 client into .build; never overwrite the distributed runtime."""
from pathlib import Path
import hashlib,os,json,subprocess,tempfile,zipfile

def main():
    dev=Path(__file__).resolve().parent
    build=dev/'.build';build.mkdir(exist_ok=True);app=build
    original=dev/'original/WOTR-GandalfV8.jar'
    crypto=dev/'tools/bcprov-jdk18on-1.86.jar'
    for name,digest in json.loads((dev/'dependencies.json').read_text(encoding='utf8')).items():
        assert hashlib.sha256((dev/name).read_bytes()).hexdigest()==digest,'Unexpected dependency: '+name
    exports=['--add-exports','java.base/jdk.internal.org.objectweb.asm=ALL-UNNAMED','--add-exports','java.base/jdk.internal.org.objectweb.asm.tree=ALL-UNNAMED']
    fp=hashlib.sha256()
    for p in [original,*sorted((dev/'src').glob('*.java')),dev/'tools/PatchKome.java',dev/'tools/flatlaf-3.7.jar',dev/'tools/pdfbox-app-3.0.8.jar',crypto]:fp.update(p.name.encode()+b'\0'+p.read_bytes())
    with tempfile.TemporaryDirectory(dir=build) as temp:
        work=Path(temp);classes=work/'classes';classes.mkdir();tools=work/'tools';tools.mkdir()
        generated=work/'KomeBuild.java';generated.write_text('public final class KomeBuild { public static final String ID="kome-'+fp.hexdigest()[:16]+'"; public static final String DATE="'+json.loads((dev/'release.json').read_text(encoding='utf8'))['build_date']+'"; }\n',encoding='ascii')
        subprocess.run(['javac','--release','8','-encoding','UTF-8','-cp',os.pathsep.join([str(original),str(dev/'tools/pdfbox-app-3.0.8.jar'),str(crypto)]),'-d',str(classes),str(generated),*[str(p) for p in sorted((dev/'src').glob('*.java'))]],check=True)
        subprocess.run(['javac',*exports,'-d',str(tools),str(dev/'tools/PatchKome.java')],check=True)
        subprocess.run(['java',*exports,'-cp',os.pathsep.join([str(tools),str(original)]),'PatchKome',str(original),str(classes),str(app/'WOTR-GandalfV8-KoME.jar')],check=True)
    # Bundle the Java-8-compatible Swing theme so double-clicking the JAR remains sufficient.
    with zipfile.ZipFile(dev/'tools/flatlaf-3.7.jar') as theme, zipfile.ZipFile(app/'WOTR-GandalfV8-KoME.jar','a',zipfile.ZIP_DEFLATED) as jar:
        existing=set(jar.namelist())
        for entry in theme.infolist():
            if entry.filename.startswith('com/formdev/') and entry.filename not in existing:jar.writestr(entry,theme.read(entry))
        jar.writestr('META-INF/FLATLAF-LICENSE',theme.read('META-INF/LICENSE'))
    with zipfile.ZipFile(dev/'tools/pdfbox-app-3.0.8.jar') as pdf, zipfile.ZipFile(app/'WOTR-GandalfV8-KoME.jar','a',zipfile.ZIP_DEFLATED) as jar:
        existing=set(jar.namelist())
        for entry in pdf.infolist():
            name=entry.filename
            if name in ('META-INF/LICENSE','META-INF/NOTICE'):jar.writestr(name+'-PDFBOX',pdf.read(entry))
            elif name not in existing and not name.startswith('org/bouncycastle/') and (not name.startswith('META-INF/') or name.startswith('META-INF/services/')) and not name.endswith('module-info.class'):jar.writestr(entry,pdf.read(entry))
    with zipfile.ZipFile(crypto) as lib, zipfile.ZipFile(app/'WOTR-GandalfV8-KoME.jar','a',zipfile.ZIP_DEFLATED) as jar:
        for entry in lib.infolist():
            if entry.filename.startswith('org/bouncycastle/') and not entry.is_dir():jar.writestr(entry,lib.read(entry))
            elif entry.filename in ('META-INF/LICENSE','META-INF/LICENSE.txt','META-INF/LICENSE.md'):jar.writestr('META-INF/BOUNCY-CASTLE-LICENSE',lib.read(entry))
    print('Built',app/'WOTR-GandalfV8-KoME.jar','build kome-'+fp.hexdigest()[:16],flush=True)
    return app/'WOTR-GandalfV8-KoME.jar'

if __name__=='__main__':main()
