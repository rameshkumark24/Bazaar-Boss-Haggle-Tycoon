# Bazaar Boss: Haggle Tycoon — Design rules

> **What every build phase follows.** These are rules a developer can apply, not a mood board.
> - The rules cover every game screen, the store listing, the website (the waitlist page) and any email.
> - They sit under [PRD.md](PRD.md) (what the product is) and [TRD.md](TRD.md) (how it's built). They decide how it looks, moves and speaks.
>
> **Decided 2026-11-24:**
> - **Direction A, "Dusk stall".**
> - **One fixed palette.** There's no light/dark switch, and the game ignores the system theme: in a game, the colours are the scene.
> - The sketch it came from is [design-preview.html](design-preview.html). Where this file and the sketch differ, this file wins.
>
> **The real test is a screenshot**, not this file. Every phase screenshots its screens (CLAUDE.md → Commands) and checks them against §10. A "no" is a bug.

---

## 0. The direction

**The bar we must clear** — two real games in this category:

| Game | What it gets right | What we match |
|---|---|---|
| **Dealer's Life 2** (Abyte; 4.6★ on Play India, COMPETITORS §1) | Each customer negotiates "according to their unique psychological traits" ([Steam](https://store.steampowered.com/app/1343670/Dealers_Life_2)), and the haggling "balances revealed info and hunches" ([Softpedia](https://www.softpedia.com/reviews/games/pc/dealer-039-s-life-2-review-534955.shtml)) | **One face, one price, one decision per round**, with personalities you can read from the face |
| **Moonlighter** (Android, through Netflix Games since May 2022; [Wikipedia](https://en.wikipedia.com/wiki/Moonlighter_(video_game))) | Shopkeeping where each customer reacts to the price you set, so you learn value from reactions. The port "could easily be mistaken as a mobile-first game" ([TouchArcade](https://toucharcade.com/2020/12/01/moonlighter-review-iphone-ipad-android/)). Its reaction icons are from memory | **A reaction readable in under a second**, and a shop that's a place you stand in, not a menu |

**Three adjectives, and what each changes:**

| Adjective | Defended by | Density | Colour | Type | Motion |
|---|---|---|---|---|---|
| **Talkative** | The bazaar talks in Tanglish and Hinglish (PRD §1); every round explains itself (DR10) | The face and its line get the top half of the haggle screen | Warm scene colours around a cool night | What a character says is set in the display face | The face changes expression with each reply |
| **Fair** | "Fair, readable and never a gamble" (PRD §1); the verdict (DR7); mood never by colour alone | Every number is labelled; nothing unlabelled is on screen | One accent, on the primary action only. Profit and loss always come with a word | Prices are the biggest numbers on screen, in tabular figures | Numbers change instantly — never a count-up that hides the value |
| **Unhurried** | No forced ads (USP); no clock in a haggle (DR12); no fake urgency (CR5) | Generous spacing, and one decision per area | A calm dark night, with no flashing reds | Sentence case; nothing shouts | Almost none. Nothing pulses, blinks or counts down, except the auction's own countdown |

**The visual idea, in one sentence:** *your stall after sunset — the customer's face across a teak counter, under a striped awning, with the price hand-lettered on a paper tag tied to the item.*

With the name covered, a screenshot is still this game because of **the awning's scalloped stripes, the teak counter and the paper price tag.** Every game screen shows at least one of the three.

---

## 1. The tells — and what we do instead

These are the look of a generated app: anonymous, "nobody decided anything". **None of them appears on any game screen, in the store listing, on the website or in an email.** Each has a replacement, because a ban without one just swaps one default for another.

**This list grows.** When a screenshot looks like a template in a new way, add the pattern here, with its replacement.

| Tell | What we do instead |
|---|---|
| **Colour and surfaces** | |
| Purple and black | Ink blue-black (hue 258) with marigold and teak (§2.3). Never a violet hue (290–330) |
| Purple-to-blue or rainbow gradients; gradient text | **No gradients at all.** Flat role colours; the awning's stripes are separate flat shapes |
| Six shades of one brand colour | One neutral ramp, **one accent used once per screen**, two status colours, and scene colours that never appear in UI chrome |
| Glass: frosted, blurred or see-through panels | Solid panels (`surface-raised`). A modal's scrim is a flat colour at 60%, with no blur |
| Glowing radial orbs, dot grids or grid lines behind content | The scene itself — awning, back wall, counter — is the background. No light pools; the bulb has no glow |
| **Layout** | |
| Three feature cards in a row | Content is one task per screen. The website lists its promises as a plain list with dividers |
| Bento grids | Not used anywhere |
| **Cards with a coloured stripe down one side** | Hierarchy comes from type and spacing. Lists use 1 dp dividers *(the waitlist page had these; replaced 2026-11-24)* |
| Everything centred in one column | **Text is left-aligned.** Only labels inside buttons are centred |
| **Type and icons** | |
| Inter, Geist or Space Grotesk, or the next default | **Roboto** (Android's own face) for the UI, and **Rozha One** for what characters say (§2.2) |
| Lucide icons; sparkle icons for "AI"; emoji as icons or decoration | **Material Symbols** (Android's own). No emoji anywhere — the font has no emoji glyphs, and the validator blocks them |
| Bold everywhere instead of hierarchy | **Two weights only** (400, 500). Hierarchy comes from size and colour *(the waitlist page used 700–800 everywhere; replaced)* |
| **Motion** | |
| Hover effects that move, grow or glow; arrows that slide | Touch-down feedback only (§4): a darker colour and a 0.97 scale |
| Animation on every element, or motion with no reason | **One motion moment:** the reveal (§4). Everything else changes instantly, or in ≤ 250 ms when a card enters |
| A 3D blob, globe or particle field; typing headlines; numbers counting up | None. The scene is flat 2D, built from shapes. Numbers change instantly. *(The waitlist page's pulsing, glowing button is replaced by a plain accent fill)* |
| **Words** | |
| Em dashes in anything a user reads | A full stop, colon or comma. En dashes only in ranges (9–5). **The PRD's on-screen text was swept 2026-11-24** |
| "It's not X, it's Y" · "not just X" · "X, reimagined" | Say the thing: "Free. No forced ads. Win by bargaining." |
| "Empower", "seamless", "unlock", "supercharge", "elevate" | Plain verbs that name the action: offer, accept, inspect, scrap, start the day |
| **Proof** | |
| A drawn phone, fake terminal, stock mockup or generated video where the app should be | Before the build: an interactive sketch, **labelled as a sketch**, with no device bezel. After the build: screen recordings and screenshots of the **release build** with realistic data *(the waitlist page drew a phone bezel; replaced)* |
| Invented people, logos, numbers or reviews | No testimonials and no download counts until they're real. Characters are game characters, plainly |
| **Newer defaults** — what a model reaches for when told "don't look generic" | |
| A cream or off-white background under a serif headline | A dark ink background. Rozha One is used only for character speech, never as a page headline *(the waitlist page was cream, `#fbf4e6`; replaced)* |
| One italic or highlighted accent word in a headline | Headlines are plain, one colour, no italics |
| Numbered "01 / 02 / 03" section labels | Sections are named, never numbered, on anything a player sees |
| Small monospace caps labels above every heading | **No caps labels, no letterspaced eyebrows.** A plain caption in sentence case, when one is needed at all *(the waitlist page had them; replaced)* |
| Pill-shaped buttons and badges everywhere | **Radius 8** for buttons and chips. No pill shapes anywhere *(the waitlist page's voice selector was pills; replaced)* |

---

## 2. The foundation

### 2.1 Spacing — one scale, nothing outside it

**Scale (dp): `4 · 8 · 12 · 16 · 24 · 32 · 48`.** No other value appears as a margin, padding or gap.

| Rule | Value |
|---|---|
| Screen side margin | 16 |
| Inside a group (between a label and its value; stacked buttons) | 8 or 12 |
| **Between groups** — at least twice the gap inside one | 24 (or 32 between major sections) |
| Panel padding | 16 |
| Modal card padding | 24 |
| List row vertical padding | 12 |
| Icon to its label | 8 |
| Tap targets apart | at least 8 |

**Why this scale fits a game:** the haggle screen is dense — a face, a price, a slider and five controls on a 360 dp phone. A 4-based scale gives the small steps that density needs, while the double-gap rule keeps groups readable.

### 2.2 Type

**Faces:**
- **Roboto**, weights 400 and 500 — Android's own face, for every piece of UI. Licence: SIL OFL 1.1 (Google Fonts).
- **Rozha One** — the display face, used **only** for what a character says, and for screen titles. Licence: SIL OFL 1.1.
  - It's a high-contrast poster face from Indian Type Foundry, with the hand-painted signboard and film-poster feel of a bazaar.
  - It also covers Devanagari, should Hindi script ever come (Q10).
  - *Considered:* **Yatra One** (the same foundry; brush-like). It loses because its brushy shapes blur at 26 dp, the size where Rozha stays crisp.
- **Both are baked to bitmaps at build time**, with no font engine at runtime (TRD TD-12, TD-26).

**The scale** — five sizes, and nothing else:

| Token | Face | Size / line height | Tracking | Used for |
|---|---|---|---|---|
| `caption` | Roboto 400 | 13 / 20 | 0 | Secondary facts: "3 left", "Day 3", rival names |
| `body` | Roboto 400 | 15 / 22 | 0 | Reasons, mood lines, list captions, dialog text |
| `label` | Roboto 500 | 15 / 20 | 0 | Buttons, list titles, the mood word |
| `title` | Roboto 500 | 20 / 26 | 0 | Screen titles that aren't speech; dialog titles |
| `display` | **Rozha One** | 26 / 29 | −1% | **What a character says**, in curly quotes |
| `tag` | Roboto 500, **tabular** | 26 / 30 | 0 | Prices on paper tags; amounts on the deal result |
| `price` | Roboto 500, **tabular** | 36 / 40 | −1% | The price on the table; your offer |

**Rules:**
- Body text sits near 1.5 line height; display near 1.1.
- **Every price, count and timer uses tabular figures**, so digits don't jiggle as they change.
- Money is "₹" plus Indian grouping (₹1,25,000), from our own formatter — never the phone's locale (TRD §4.2).
- Short forms (₹2.5L, ₹1.2Cr) only where space is short: the top bar, from 1 crore up.
- **Sentence case everywhere.** No all-caps, and no letterspaced labels.
- **Text size** (F13) has four steps, multiplying every size above: **S 0.9 · M 1.0 · L 1.2 · XL 1.4**.
  - The default follows Android's font scale: below 0.95 → S, below 1.1 → M, below 1.3 → L, otherwise XL. The in-game setting overrides it.
  - Layouts reflow; they never clip (§6).
- **Line length** is capped at 70 characters. On wide screens the content column stops at 480 dp (§2.6).

### 2.3 Colour — by role, from a tool

**Every colour comes from [`docs/design/palette.py`](design/palette.py):** OKLCH in, gamut-mapped hex out, every text pair contrast-checked. Change a colour there, run it (`python -I docs/design/palette.py`), and it fails if any pair drops below its minimum.

**UI roles — the only colours UI chrome may use:**

| Role | OKLCH | Hex | Use | Contrast |
|---|---|---|---|---|
| `surface` | 0.22 0.035 258 | `#101b2b` | Screen background: the night | — |
| `surface-raised` | 0.28 0.035 258 | `#1e293a` | Panels, modal cards | — |
| `surface-tonal` | 0.36 0.035 258 | `#323e50` | Secondary buttons, quick-offer chips, selected fills | text 9.7:1 |
| `surface-tonal-pressed` | 0.42 0.035 258 | `#414e60` | Pressed tonal controls | text 7.5:1 |
| `divider` | 0.38 0.030 258 | `#394353` | Dividers, the slider track, input borders | text-muted 5.4:1 |
| `text` | 0.96 0.010 85 | `#f5f1ea` | All primary text | 15.4:1 on surface |
| `text-muted` | 0.80 0.020 258 | `#b6becb` | Secondary text, icons | 9.3:1 on surface |
| `text-disabled` | 0.62 0.020 258 | `#7f8792` | Disabled labels | 4.0:1 on raised |
| **`accent`** | 0.80 0.160 72 | **`#fcaa2b`** | **Marigold. The one filled primary button on a screen. Nothing else** | on-accent 9.0:1 |
| `accent-pressed` | 0.72 0.150 68 | `#e1901f` | The pressed primary button | on-accent 6.8:1 |
| `on-accent` | 0.22 0.035 258 | `#101b2b` | The label on the primary button | — |
| `profit` | 0.80 0.140 150 | `#75d78d` | Profit amounts, **always with the word** "profit" | 8.2:1 on raised |
| `loss` | 0.72 0.160 28 | `#f9786a` | Loss amounts, **always with the word** "loss" | 5.5:1 on raised |

**Scene roles — illustration only, never UI chrome:**

| Role | Hex | Role | Hex |
|---|---|---|---|
| `scene-awning` | `#dd6d45` | `scene-brass` | `#cda448` |
| `scene-paper` (awning stripes, tags) | `#f1e7d2` | `skin-1 … skin-5` (face kit) | `#e1bc9d` `#c99976` `#b77d56` `#996341` `#754932` |
| `scene-ink` (text on tags; 13.0:1) | `#192230` | `hair` | `#291f18` |
| `scene-counter` · `scene-counter-edge` | `#70482b` · `#885e41` | | |

**Ramps** (9 steps each, from the tool):
- Neutral, hue 258: `#f5f9ff #d7e6fc #bdcce2 #97a6bb #738196 #515e72 #374355 #1e293a #0c1626`.
- Accent, hue 72: `#ffebd3 #ffce91 #fcaa2b #d78c00 #af7100 #885700 #6b4300 #4f3100 #341f00`.
- New roles come from these ramps.

**Rules:**
1. **One accent, on the primary action only.** At most one filled marigold button per screen. Marigold never colours text, icons, borders or backgrounds.
2. **Status colour never stands alone.** "+₹80 profit", "−₹50 loss". Mood is a face plus a word (F2).
3. **One fixed palette** (decided 2026-11-24). The game ignores Android's dark/light setting, and the status-bar icons are always light.

### 2.4 Surfaces

- **Solid only.** No blur, no transparency except the modal scrim (`surface` at 60%), and no gradients.
- **One shadow, `shadow-raised`:** 0 8 dp 24 dp, black at 35%. **Only the bottom panel, modal cards and the paper price tag cast it.** Nothing else has a shadow.
- **Borders:** 1 dp, and only for dividers (`divider`), inputs, and the selected state (`text`, over a tonal fill). Buttons have no borders.
- **Radius scale: `4 · 8 · 24`.**
  - **4:** tags, patience marks, meter ends.
  - **8:** buttons, chips, inputs, list-row press fills.
  - **24:** panels, modal cards, sheets.
- **Corners that nest stay concentric:** outer = inner + the padding between. A panel (24) with 16 padding holds buttons of 8.

### 2.5 Icons

- **Material Symbols Outlined** (Apache-2.0): weight 400, optical size 24, grade 0 — matched to Roboto 400.
- **Filled** (FILL 1) only for an active or selected state, e.g. `volume_off` while muted.
- **Size:** 24 dp, centred in a 48 dp target.
- **Colour:** `text` or `text-muted`. Never accent.
- **Baked:** only the glyphs we use go into an atlas at build time (TRD TD-26). No other icon set.

### 2.6 Large screens and landscape

- **The game stays portrait** (PRD F20; `appCategory="game"` keeps the lock on tablets, TRD [T11]).
- On screens wider than 480 dp, **the scene stretches the full width** — the awning, wall and counter — while **panels and text stay in a 480 dp column**. Text lines stay under 70 characters.
- Text inside the column stays left-aligned.

### 2.7 The component set

These are the only building blocks. Each is one scene2d widget style, built in code (TRD TD-19). Every interactive component has the states listed.

| # | Component | Spec | States |
|---|---|---|---|
| 1 | **Button** | **Primary:** `accent` fill, 52 dp high, radius 8, `label` in `on-accent` — at most one per screen. **Secondary:** `surface-tonal`, 48 dp. **Text:** no fill, `text-muted`, 48 dp. **Icon:** 48×48 dp, a 24 dp glyph | **Pressed:** on touch-down, within 100 ms — the pressed colour and a 0.97 scale. **Disabled:** a tonal fill, `text-disabled`, ignores taps, stays 48 dp. **Busy:** while a commit runs, taps are ignored; past 400 ms the label becomes "Saving…" |
| 2 | **Quick-offer chip** | Secondary-button style, 48 dp high: "−10%" and "−25%" when buying (a true minus sign, U+2212), "+10%" and "+25%" when selling, and "Last price?". One tap makes the offer (F2 AC10); a chip that can't move the price by a bazaar step is disabled | As Button. After "Last price?" is used, it's disabled for that haggle |
| 3 | **Offer slider** | A 4 dp `divider` track; the part below the thumb in `text-muted`; a 24 dp `text` thumb (28 dp while dragging) inside a 48 dp target. The value shows above it in `price`. It moves in bazaar steps (TRD §4.2). **Its whole 48 dp target stays at least 32 dp from both screen edges**, clear of the back gesture's 30 dp zone (EDGE-CASES IN-4). On Android 10+ its area is also excluded from the back gesture. *Decided 2026-11-25 from Phase 4's measurement; it was 24 dp* | Disabled when you can't afford any offer. Dragging |
| 4 | **Price tag** | `scene-paper` with a notch and a hole, `scene-ink` text, a `caption` line ("Asking") over a `tag` amount, radius 4, rotated at most 4°, with `shadow-raised` | Static |
| 5 | **Face** | The face kit (IDEA D2) in `skin-*` and `hair`. **The mood word always sits beside or below it** | Expression changes are a 150 ms crossfade |
| 6 | **Speech line** | `display`, in curly quotes. Two lines at M; may wrap to three at XL; never truncated. Long-press reports the line (F19) | — |
| 7 | **Meter** | **Patience:** five 10 dp marks, radius 4 — filled in `text`, empty as a 1.5 dp `text-muted` outline — plus the words "3 left". **Goal bar:** an 8 dp `divider` track with a `text` fill, plus "₹18,400 of ₹25,000" | Static; values change instantly |
| 8 | **Panel** | `surface-raised`, radius 24 on its exposed corners, 16 padding, `shadow-raised`. It holds a screen's controls at the bottom | — |
| 9 | **Modal card** | `surface-raised`, radius 24, 24 padding, at most 400 dp wide, over the scrim. `title`, then `body`, then actions at the bottom right: [secondary or text] [primary] | Enters in 250 ms; leaves in 150 ms (§4) |
| 10 | **Banner** | `surface-tonal`, radius 8, 12 padding, under the top bar: an icon, a `body` message, and one text-button action | **Error** banners stay until the problem is fixed. **Info** banners leave after 4 s |
| 11 | **List row** | At least 56 dp high (72 with a caption). A 40 dp item drawing, a `label` title with a `caption`, and the price on the right in tabular `label`. A 1 dp divider, inset 16 | Pressed: a tonal fill. Selected: a 1 dp `text` border |
| 12 | **Empty state** | A `title` saying what goes here, a `body` line saying how to get it, and **one** secondary button. Left-aligned. Optionally one small scene object, such as an empty gunny bag | — |
| 13 | **Skeleton** | Only where something really loads (the shop's product list later; a launch slower than 300 ms): static `surface-tonal` blocks shaped like the rows they replace. **No shimmer animation** | — |

---

## 3. The eight that need no taste

1. **Whitespace:** double what feels right. Between groups, 24 or more (§2.1).
2. **Alignment:** everything snaps to the 16 dp side margins and the spacing scale. Text is left-aligned.
3. **Contrast:** every pair is checked by `palette.py`. Nothing below 4.5:1 for text, or 3:1 for controls.
4. **One accent:** one marigold primary button per screen, at most.
5. **Line length:** 70 characters at most.
6. **Consistent radius:** 4, 8 or 24, with nested corners concentric.
7. **A visible pressed state** on every interactive element, on touch-down. A control with no pressed state feels broken on a phone even when it works.
8. **Disabled and busy states** on everything interactive (§2.7).

---

## 4. Mobile feel and motion

**Feel:**
- **Scrolling** (godown, kanakku, settings): use scene2d's ScrollPane with fling on, an overscroll of at most 32 dp that settles back in 200 ms, and no bounce glow — close to Android's own feel.
- **Transitions are Android-style**, never iOS's sideways push. The incoming screen fades in and grows from 0.95 to 1.0 in 220 ms (ease-out); the outgoing one fades out in 150 ms.
- **Feedback on touch-down, not release:** the pressed state appears within 100 ms. **No animation ever blocks the next tap.** Every animation can be interrupted.
- **Haptics on meaningful moments only:** a deal closes, an auction is won, a tier-up. A 20–30 ms pulse, only with the Vibration setting on. Never on ordinary taps.
- **No keyboard anywhere:** nothing is typed (PRD §5).
- **Optimistic, but honest:** the pressed state shows instantly. A result appears only after its commit (TRD §3.2), hidden under the customer's reaction, which takes 150–300 ms.

**Motion, by how often it happens:**

| How often | What | Motion |
|---|---|---|
| Many times a day | Offers, counters, mood changes, price changes, patience | **None, or a change under 150 ms.** The face crossfades in 150 ms; numbers and words change instantly |
| A few times a day | Modal cards (deal result, confirmations), banners, screen changes | **200–300 ms.** In with ease-out; out faster, in 150 ms, with ease-in-out. Starting at 0.95 scale and transparent — never from nothing |
| Rare | First launch, the tier-up, an empty state | Plain — the same 250 ms card. No confetti, glow or particles |
| **Once a day — the one motion moment** | **The reveal** (screen 6a) | See below |

**The one motion moment: the gunny bag emptying.**
- **What happens:** each item drops out of the bag onto the counter — 280 ms, ease-out, falling at most 24 dp and fading in from 0.95 scale — with a 120 ms pause before the next. A tap shows the next item at once; back shows them all at once (PRD F5 AC10).
- **Why it's this moment:** it's the day's peak, "the thrill of a lot that turns out better than it looked" (PRD §1 core feeling; the peak–end rule, IDEA §3.2). It's the one place we slow down on purpose.
- **Nothing else** in the app gets expressive motion.

**Rules for every animation:**
- Animate only position, scale and opacity.
- Springs never bounce.
- **Animation runs in libGDX's render loop.** The game never waits on a file or a network call to draw a frame (TRD §4.10).
- **When Android's "Remove animations" is on** (animator duration scale 0): fades stay, but shortened to 100 ms; all movement and scaling go. The reveal's items simply appear.
- **3D:** none. The subject is a flat stall.
- **Video** (the store, reels, later): only **screen recordings of the release build** (`adb shell screenrecord`) with realistic data. Titles and captions are made in code. Captions for any speech come from Whisper, run on this PC. Nothing plays sound until tapped.

---

## 5. The five states, on every screen

Every screen designs all five states, and every phase screenshots them. Per-screen detail is in [APP-FLOW.md §1.8](APP-FLOW.md#18--the-table--one-row-per-screen). The rules:

| State | Rule in this game |
|---|---|
| **Loading** | Almost nothing loads: the save is local and loads in ≤ 100 ms (TRD §0.4). Android's splash covers the launch. A **skeleton shaped like the rows** appears only where loading is real: the shop's products later, or a launch slower than 300 ms |
| **Error** | Says what happened and what to do, beside what failed, with a retry where one exists. **Nothing the player did is lost.** Example: "Couldn't save. Phone storage is full. Free up some space and the game will save again." as a lasting error banner, while play goes on |
| **Empty** | **Two different states** wherever a list can be empty: *never had any* (what goes here, and how to get it) vs *filtered to nothing* ("Nothing matches this filter", "Clear filter") |
| **Offline** | v1 is offline by design, so this state doesn't exist in v1. Later, ad offers say "No ad right now" and the shop says "Can't reach Google Play" — and the game around them keeps working |
| **Success** | Every action shows its result; silence reads as failure. A deal shows the deal result card; put-on-display moves the item into its slot; Share opens the share sheet; Start over shows the voice picker. **Autosaves stay silent** — the result on screen is the confirmation |

---

## 6. Accessibility

- **Tap targets:** at least 48 × 48 dp, and at least 8 dp apart.
- **Text scales with Android's font setting without clipping** (§2.2). Every v1 screen is screenshotted at **XL text on a 320 dp-wide screen** (EDGE-CASES has the commands). Clipping is a bug.
- **Contrast** is measured by `palette.py`: 4.5:1 for text, 3:1 for large text and controls. Nothing is judged by eye.
- **Colour is never the only signal:** mood is a face plus a word, money comes with "profit" or "loss", patience is marks plus "3 left".
- **Reduce motion:** with "Remove animations" on, the fades stay and the movement goes (§4).
- **Screen readers:** libGDX draws its own screens, so TalkBack can't read them — a known limit (PRD §5). Two mitigations:
  - When TalkBack is on, the game makes **one spoken announcement** that it can't be read by a screen reader yet (EDGE-CASES).
  - Every icon button still gets a text label in code, ready for future support.
- **Forms:** none in v1. Later, the age screen uses a number picker, not the keyboard.
- **Captions:** every video with speech has them (§4).

---

## 7. The words on screen

**Rules:**
- **Buttons start with a verb that names the action:** "Offer ₹340", "Accept ₹400", "Walk away", "Inspect", "Scrap 3 items", "Keep all", "Start Day 5", "Close for the day", "Upgrade to shop", "Share deal", "Send report", "Delete my game". Never "OK", "Submit" or "Learn more" on their own.
- **Errors say what to do next.** No "Oops", no "Something went wrong", no exclamation marks.
- **No "Successfully".** Autosaves say nothing at all. No "Please" in routine steps. Sentence case.
- **Empty states** say what goes here, and offer one action.
- **Confirmations name the action and the thing:**
  - "Scrap 3 items for ₹120?" → **Scrap items** · Cancel
  - "Start over? This deletes your game, its backups and your settings on this phone." → **Delete my game** · Cancel
  - "Leave the auction? You won't win the lot." → **Leave auction** · Stay
  - **Where an action can be undone, there's no question** (e.g. taking an item off display).
- **Punctuation:**
  - curly quotes (“ ” ‘ ’) and a real ellipsis (…)
  - an en dash only in ranges (9–5)
  - **no em dashes**
  - negative money with a true minus sign (−₹50) **and** the word "loss"
- **Dialogue is the exception, partly.** What characters say may exclaim ("Okay okay, vaanga!") — that's their voice. It still never uses a word from PRD §9's Never lists.
- **The game's own words are fixed** in PRD §9 (Vocabulary). Use those exact words.

**Swept on 2026-11-24** (PRD, APP-FLOW, TRD and the website now use the right column):

| Was | Now |
|---|---|
| "Annoyed — you barely moved" | "Annoyed. You barely moved." |
| "Couldn't save — phone storage full" | "Couldn't save. Phone storage is full." |
| "This save is from a newer version — update the game" | "This save is from a newer version. Update the game to keep playing." |
| "Couldn't read your save — starting fresh. Sorry." | "Couldn't read your save or its backups, so a new game has started." |
| "Welcome back — here's a cheap starter lot" | "Welcome back. Here's a cheap starter lot." |
| "Welcome back — your progress from <date> is here" | "Welcome back. Your progress from <date> is here." |
| "Day N done — Start Day N+1" | "Day N done", with the button **Start Day N+1** |
| "Game money — no real money" (the deal card's line) | **"Game money. No real money."** |

---

## 8. Where every asset comes from

**Rules:**
- Nothing comes from an image search.
- No other brand's logo, name or icon appears in the game, its store icon, its screenshots or **its store keywords**. The positioning line that names Bid Wars and Bid Master (PRD §1) is internal: **it never appears in the listing.**
- "Free" often means personal use only, so the licence is always checked.
- **AI-generated art, music or voices:** none are planned. If that ever changes, the generator's terms must allow commercial use first. **Never a cloned voice of a real person.**

| Asset | Source | Licence | Credit |
|---|---|---|---|
| Roboto (UI face) | Google Fonts | SIL OFL 1.1 | The OFL text in Credits & licences |
| Rozha One (display face) | Google Fonts (Indian Type Foundry) | SIL OFL 1.1 | The OFL text in Credits & licences |
| Material Symbols (icons) | Google, material-design-icons | Apache-2.0 | The Apache notice in Credits & licences |
| Faces, items, the stall scene, the app icon | **Made by the owner, as shapes in code** (IDEA D2) | Owner's own | — |
| Sound effects | Made with jsfxr (owner's own), or CC0 packs (e.g. Kenney) | Owner's own / CC0 | Optional for CC0; listed anyway |
| Music | None in v1. If added: CC0 only (RESEARCH L-11) | CC0 | Listed |
| Voices | None | — | — |
| Store screenshots and video | The **release build**, captured with `adb` | Owner's own | — |
| Website share image (`og.png`) | Rendered from `site/og-card.html`, made by the owner | Owner's own | — |
| Website fonts | The phone's own UI font (no external loads, to keep the page's "loads nothing from other sites" promise) | — | — |

---

## 9. Applied to existing work (2026-11-24)

| Where | What changed |
|---|---|
| **Waitlist page** (`site/bazaar-boss/index.html`) | Moved to the dusk palette: no cream background. Replaced: the striped cards, letterspaced caps labels, pills, the pulsing glow, the drawn phone, bold-everywhere, centred sections, seven radii and the em dashes. The haggle demo is labelled as a sketch |
| **Share image** (`site/og-card.html` → `og.png`) | Re-rendered in the dusk palette |
| **Waitlist form text** (`site/README.md`) | Em dashes and the exclamation mark removed from the form's title, description and confirmation |
| **The design preview** | Kept as the sketch it is. Its outlined buttons became **tonal buttons** here (§2.4: borders only for dividers, inputs and the selected state) |
| **The empty project** | Its placeholder background (`#23305e`) becomes `surface` in M0, when the placeholder is replaced |

---

## 10. ⭐ The visual checklist

**Every build phase, and the final quality check:**
1. Screenshot each screen, including its empty and error states and its XL-text version.
2. Answer these yes/no questions. **Every answer must be yes.**

1. Is every gap and padding one of 4, 8, 12, 16, 24, 32 or 48 dp?
2. Is every text size one of 13, 15, 20, 26 or 36 (times the text-size step)?
3. Is there **at most one marigold element**, and is it the primary action?
4. Is all text left-aligned, except labels inside buttons?
5. Is mood shown as a face **plus a word**, and does every profit or loss figure carry its word?
6. Do prices use ₹, Indian grouping and tabular figures?
7. Is Rozha One used **only** for what a character says, and for screen titles?
8. Is nothing drawn under the status bar, a display cutout or the navigation bar?
9. Is every tap target at least 48 dp, and 8 dp from the next?
10. Are all corners 4, 8 or 24, with nested corners concentric?
11. Do **only** the panel, modal cards and the price tag cast a shadow, with borders only on dividers, inputs and the selected item?
12. At XL text on a 320 dp-wide screen, is nothing clipped or overlapping?
13. Is the visible text free of em dashes, "Oops", "Please", "Successfully", and exclamation marks outside dialogue?
14. Is the screen free of every pattern in §1?
15. **With the name covered, is it still this game?** On game screens, is the awning, the counter or a price tag visible?
