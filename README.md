# WOTR-GANDALF-KOME

War of the Ring Java client with **Kings of Middle-earth** and **Kings + Lords of Middle-earth** game options.

## Unofficial fan-made project

War of the Ring and its expansions are published by **Ares Games**, with game design by **Roberto Di Meglio, Marco Maggi, and Francesco Nepitello**. The original Java client is by **Sean McCarthy**, with additional programming by **Andrew Poulter** and **Peter Majek**. Its original copyright notice is **© 2004–2017 Sean McCarthy/Andrew Poulter/Peter Majek**.

We do not claim ownership of the game, the original Java client, or any associated rules, artwork, names, characters, or other third-party materials. All rights remain with their respective owners. This project is not affiliated with, endorsed by, or sponsored by the original creators, publishers, or rights holders.

See [CREDITS.md](CREDITS.md) and the unchanged [original client README](WOTR-GANDALF/README.pdf) for full attribution and notices. Please support the creators by purchasing the physical game and expansions, and use the official rulebooks for play.

## Run

1. Download **WOTR-KoME.zip** from the release's **Assets** section, then extract it.
2. Open the `WOTR-GANDALF` folder. Keep its asset subfolders intact.
3. With Java 8 or newer installed, open `WOTR-GandalfV8-KoME.jar`.
4. Choose **New Game > Kings of Middle-earth**, then Kings alone or Kings + Lords.

The sovereign board opens automatically. The client supplies pieces and play tools; players apply the game rules.

## Combat cards and multiplayer

Adds an **Autoreveal Combat cards** checkbox with the **F3** shortcut. It is enabled by default and synchronized between players. Turn it off to keep chosen combat cards hidden while resolving intervening abilities, then press **F2** to reveal.

Multiplayer now checks that both players use the same KoME gameplay build before connecting. Older releases without this check are incompatible with this build.

Fixes several multiplayer freeze conditions involving secure dice rolls, chat typing, and UI updates.

Turning the checkbox back on does not immediately reveal cards already waiting. On keyboards that reserve function keys for system controls, use Fn with the function key or use the menu. An unresponsive secure roll is cancelled after 30 seconds; reconnect before trying again. Cancelled rolls do not produce a verified result.

## Source and build files

[source/](source/) contains the decompiled original client, the KoME Java sources, and the build tools used for this client. See [source/README.md](source/README.md) for the reproducible build procedure and the limitations of the decompiled listing. Original copyright notices and rights remain applicable.
