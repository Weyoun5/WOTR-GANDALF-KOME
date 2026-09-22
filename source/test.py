"""Run regression checks in isolated runtime copies; requires a desktop."""
from pathlib import Path
import os
import shutil
import subprocess
import sys
import tempfile
import build

source = Path(__file__).resolve().parent
app = source.parent / 'WOTR-GANDALF'

def main():
    jar = build.main()
    work = Path(tempfile.mkdtemp(prefix='tests-', dir=source / '.build'))
    classes = work / 'test-classes'
    classes.mkdir()
    test = work / 'test-client'
    test.mkdir()
    for path in app.iterdir():
        if path.is_dir() and path.name != 'logs':
            shutil.copytree(path, test / path.name)
        elif path.name == 'messages.properties':
            shutil.copy2(path, test / path.name)
    shutil.copy2(jar, test / jar.name)
    (test / 'logs/auto').mkdir(parents=True)
    shutil.copytree(source / 'original', work / 'original')
    shutil.copytree(source / 'tests', work / 'tests')
    subprocess.run(['javac', '--release', '8', '-encoding', 'UTF-8', '-Xlint:-options',
                    '-cp', str(jar), '-d', str(classes),
                    *[str(p) for p in (source / 'tests').glob('*.java')]], check=True)
    # Existing runners use their parent as the scratch directory. Adapt their
    # classpath separators and app path so they also work on macOS/Linux.
    for name in ([] if '--version-only' in sys.argv else ['run_tests.py', 'run_combat.py']):
        text = (source / 'tests' / name).read_text(encoding='utf8')
        text = 'import os\n' + text
        text = text.replace("app=dev.parent/'WOTR-GANDALF'", "app=dev/'test-client'")
        text = text.replace("+';'+jar", "+os.pathsep+jar")
        text = text.replace("+';WOTR-GandalfV8-KoME.jar'", "+os.pathsep+'WOTR-GandalfV8-KoME.jar'")
        runner = work / 'tests' / name
        runner.write_text(text, encoding='utf8')
        subprocess.run([sys.executable, str(runner)], check=True)
    if '--version-only' not in sys.argv:
        for cls in ['KomeThreadingChecks', 'KomeConnectionChecks']:
            with (work / (cls + '.log')).open('w') as output:
                subprocess.run(['java', '-Dkome.mode=newKOME', '-cp', os.pathsep.join([str(classes), str(jar)]), cls],
                               cwd=test, stdout=output, stderr=subprocess.STDOUT, timeout=25, check=True)
            print(cls, 'PASS', flush=True)
    for role in ['true', 'false']:
        for kind in ['different', 'legacy', 'disconnect', 'silent']:
            log = work / ('version-' + kind + '-' + role + '.log')
            with log.open('w') as output:
                subprocess.run(['java', '-Dkome.mode=newKOME', '-cp', os.pathsep.join([str(classes), str(jar)]),
                                'KomeVersionCheck', kind, role], cwd=test,
                               stdout=output, stderr=subprocess.STDOUT, timeout=20, check=True)
            print('VERSION CHECK PASS', kind, 'host=' + role, flush=True)
    print('Version checks passed.' if '--version-only' in sys.argv else 'All regression checks passed.', 'Logs:', work, flush=True)

if __name__ == '__main__':
    main()
