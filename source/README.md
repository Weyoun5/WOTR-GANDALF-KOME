# Client source and build

The original client is copyright © 2004–2017 Sean McCarthy/Andrew Poulter/Peter Majek. See [credits and rights](../CREDITS.md) and the unchanged [upstream README](../WOTR-GANDALF/README.pdf). This folder does not grant a new licence over upstream code or game assets.

## Contents

- `decompiled/`: all 202 original class listings produced with CFR 0.152. These are an inspection aid, not the original authors' source. CFR renames case-colliding identifiers on Windows (for example `i_0.java`) and leaves decompilation artefacts, so these files are not compiled wholesale.
- `src/`: the actual KoME Java extension sources.
- `original/WOTR-GandalfV8.jar`: the preserved original bytecode used as the build input.
- `tools/PatchKome.java`: the ASM patcher that applies hooks to the original classes and adds the compiled extension.
- `tools/PolishAssets.java`: the historical artwork preparation utility. It expects development artwork outside this repository and is not needed to rebuild the client using the shipped assets.
- `tests/`: regression tests and bounded diagnostic probes. Historical probes describe upstream behavior and are excluded from the regression runner; the current threading and connection checks verify the repaired paths.

## Rebuild

Requires Python 3.10+ and JDK 21 (`java` and `javac` on PATH). No dependency downloads are needed. From the repository root:

```
python source/build.py
```

Output: `source/.build/WOTR-GandalfV8-KoME.jar`. The extension targets Java 8 bytecode. To play, copy this JAR into a copy of `WOTR-GANDALF` with all its shipped asset folders. The script leaves the distributed JAR and player preferences untouched.

The build computes a stable gameplay identifier from the original JAR, extension Java sources, and patcher. A source change changes the identifier used by the multiplayer version check. JAR timestamps may vary; comparing uncompressed entries verifies equivalent build output.

## Verify

```
python source/test.py
```

This first builds the client, then tests both Kings configurations, base/LoME behavior, combat-card UI, multiplayer behavior, and version mismatch rejection. It creates isolated runtime copies under `source/.build/`; it never uses or changes the player's preferences. Tests open Swing windows and require an interactive desktop. The separate diagnostic probes are provided for investigation and are not part of the passing regression suite.
