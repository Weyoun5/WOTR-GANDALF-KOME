KINGS OF MIDDLE-EARTH FOR THE WOTR JAVA CLIENT

Start
  Open WOTR-GandalfV8-KoME.jar. Start-KoME.cmd is an optional launcher that
  ensures the client starts in its asset folder.
  Under New Game, open Kings of Middle-earth and choose:
    Kings of Middle-earth
    Kings + Lords of Middle-earth
  The sovereign corruption board opens in its own window automatically.
  Ctrl+K or Kings > Sovereign corruption board reopens it.
  The main window keeps just Free, Shadow, and The Hunt.
  The board has three sections: Sovereigns, Kings Reference, and Rules.
  Use each sovereign's Corruption tiles button to draw or manage tiles.
  Kings Reference on the board contains all 13 ability sheets; click one
  to read its full-size original art. No duplicate sheets are in reserves.

Components
  Regular sovereigns start at Brand: Dale; Dain: Erebor; Denethor: Minas
  Tirith; Theoden: Edoras; Thranduil: Woodland Realm.
  Five Awakened figures, the FP Ruler die, and the five ruler tokens start
  in Free Peoples reinforcements. Five Corrupted figures, the Shadow Ruler
  die, the three Dark Chieftains, and the Corruptor counter start in Shadow
  reinforcements.
  Awaken and Corrupt pull the appropriate physical figure from reserves
  into the sovereign's current location. The outgoing figure returns to
  its side's reinforcements. Regular swaps the original figure back in.
  Move sovereign figures with the normal client drag controls.
  Awakened sovereigns use their nation colors: Brand blue, Dain brown,
  Denethor dark blue, Theoden dark green, and Thranduil green.
  Neutral figures have a gray wash that retains a subtle nation tint.
  Corrupted figures are black with charcoal highlights and red base rims.
  Awakened figures have gold base rims. Names also identify the current form.
  Kings Reference has five Awakened sovereign ability sheets, five Corrupted
  sovereign ability sheets, and the Black Serpent, Shadow of Mirkwood, and
  Ugluk ability sheets. Tokens, dice, Corruptor counter, and Helm's Deep
  overlay are available in the normal reinforcements areas.

  The two Kings Hunt tiles start in the Hunt Pool. The 19 core event cards
  are installed in the native decks: 11 replacements and 8 additions.
  Character and Strategy event cards use the correct Free Peoples or Shadow
  deck and exactly the same small/full backs as the original cards there.
  Full event cards match the client's 192 x 352 faces; compact cards are
  112 x 42, using the original small-card backgrounds and type symbols,
  with bold small-cap event/combat titles and the combat number.
  See kome/CARD-REPLACEMENTS.txt for the rulebook's 11 replacements.
  The workshop's two promotional cards are preserved in the export folders
  but are not part of the default decks.

Sovereign corruption
  Take the Shadow seat with the client's usual controls to inspect hidden
  corruption. Use Corruption tiles beside the ruler on the sovereign board.
  Draw one to three tiles, choose one to keep, and return the others.
  Tiles are removed from the shared Hunt Pool while drawn or held.
  Returned tiles are announced to both players; kept Kings tiles are public.
  Reveal, hide, return, or discard individual tiles with the panel controls.
  Draw one at a time when applying stopping or redraw rules. Players handle
  special tiles, Smeagol tiles, and card effects using these manual controls.
  Awakening or changing status does not automatically return/discard tiles.
  Use Return all tiles or the individual controls as appropriate.

Dice and tokens
  Drag ruler dice from reinforcements to the dice pools; roll them with the
  client's normal Roll command. Move used dice with the normal controls.
  Players decide when dice enter/leave play and which die to discard.
  In the combined mode, flip a spent ruler token to Dismay and move it to
  Shadow. Move a spent Dismay token out of play and reroll normally.
  Dismay is the recommended optional rule and can simply be left unused.
  Normal Flip turns a ruler token into its Dismay side, and flips it back.
  Move a ruler token back to reinforcements and flip it back when needed.

Kings + Lords
  The original LoME implementation supplies its pieces, decks, dice and
  companion selection. The Rules tab includes KoME pages 17-18:
    - Dismay tokens.
    - Only normal Action dice or the Ruler die qualify for the roll discard.
    - Smeagol tiles are ignored and redrawn for corruption.
    - Captain of Despair dice do not count for corruption: keep them separate.
    - Keeper / Lesser Minion Eyes count; when spent for corruption they go
      to the Free Peoples player's used dice.
    - Council Action tokens can bypass Dark Chieftain entry prerequisites.
    - Characters without a Nation icon cannot remove sovereign corruption
      or eliminate Corrupted Sovereigns.
  These are manual tools; the client does not adjudicate game rules.

Saving, undo and multiplayer
  Save and load through the normal client menus. Start the matching game
  type before loading a save. A mismatch is rejected before loading.
  Use Undo Kings action for actions made through the Kings panel.
  The normal undo controls handle normal client actions.
  Both players need the KoME JAR and the kome asset folder, and must choose
  the same game type and build. Saves made before physical reserve figures
  were added require the earlier pre-reserve client, not included here.
  Start a new game in the current JAR to use the physical reserve figures.
  Saves from the reserve-figure build remain compatible with this UI/art
  update. Redundant reference-sheet copies are removed when those saves load.
  Share the complete WOTR-GANDALF folder, omitting your
  personal preferences and logs when preparing a copy for another player.
  Hidden corruption is concealed in the UI; the command log contains tile
  identifiers, as this is a manual tabletop tool rather than a new secure
  hidden-information protocol.

Files
  WOTR-GandalfV8-KoME.jar is the current client. Keep all supplied asset folders.
  kome/ contains the KoME artwork and rulebook. README.pdf is the original
  client manual. The game creates fresh preferences and log folders on launch.
  No personal preferences, saved logs, old JARs or development files are included.
  Java 8 or newer is required.
