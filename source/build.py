"""Build the client from preserved original bytecode and the extension sources."""
from pathlib import Path
import hashlib
import os
import subprocess
import tempfile

source = Path(__file__).resolve().parent
original = source / 'original/WOTR-GandalfV8.jar'
build = source / '.build'
build.mkdir(exist_ok=True)
exports = ['--add-exports', 'java.base/jdk.internal.org.objectweb.asm=ALL-UNNAMED',
           '--add-exports', 'java.base/jdk.internal.org.objectweb.asm.tree=ALL-UNNAMED']

def run(*args):
    subprocess.run([str(arg) for arg in args], check=True, cwd=source)

def main():
    inputs = [original, *sorted((source / 'src').glob('*.java')), source / 'tools/PatchKome.java']
    fingerprint = hashlib.sha256()
    for path in inputs:
        fingerprint.update(path.name.encode('utf8') + b'\0' + path.read_bytes())
    build_id = 'kome-' + fingerprint.hexdigest()[:16]
    # A fresh private directory prevents obsolete classes entering a later build.
    with tempfile.TemporaryDirectory(prefix='compile-', dir=build) as temporary:
        work = Path(temporary)
        classes, tools = work / 'classes', work / 'tools'
        classes.mkdir(); tools.mkdir()
        identity = work / 'KomeBuild.java'
        identity.write_text('public final class KomeBuild { public static final String ID="' + build_id + '"; }\n', encoding='utf8')
        run('javac', '--release', '8', '-encoding', 'UTF-8', '-Xlint:-options', '-cp', original,
            '-d', classes, identity, *sorted((source / 'src').glob('*.java')))
        run('javac', *exports, '-d', tools, source / 'tools/PatchKome.java')
        output = build / 'WOTR-GandalfV8-KoME.jar'
        run('java', *exports, '-cp', os.pathsep.join([str(tools), str(original)]),
            'PatchKome', original, classes, output)
    print('Built', output, '\nGameplay build:', build_id, flush=True)
    return output

if __name__ == '__main__':
    main()
