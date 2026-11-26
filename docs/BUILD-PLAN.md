# Bazaar Boss: Haggle Tycoon — Build Plan

The phase-by-phase plan for building the game. Written 2026-11-25 from all seven planning docs, each read first: [PRD.md](PRD.md) · [TRD.md](TRD.md) · [APP-FLOW.md](APP-FLOW.md) · [DESIGN.md](DESIGN.md) · [EDGE-CASES.md](EDGE-CASES.md) · [MONETISATION.md](MONETISATION.md) · [LLD.md](LLD.md). None is missing.

---

## How to use this file

**When the owner says "build the next phase":**
1. Read **CURRENT STATE** at the bottom.
2. Take the **lowest-numbered phase that isn't DONE, whose DEPENDS ON are all DONE**. If it's an owner phase, ask the owner where it stands instead of building.
3. If **PLAN MODE: yes**, present a plan and get the owner's approval before writing code.
4. Set **STATUS: IN PROGRESS**, and write the plan into **PROGRESS**.
5. Build. **Update PROGRESS as you go**: what's done, what's left, and where each piece of evidence is. A compaction, a crash or a closed window then loses nothing.
6. Check every item in **DONE WHEN** and record its evidence in PROGRESS.
7. Set **STATUS: DONE**, and update **CURRENT STATE**: what's built, what's next, sessions used.
8. Stop and report:
   - what to test on the phone
   - the known issues; anything you choose to live with goes in KNOWN ISSUES, not only in the chat
   - whether the doc check is due (the rule below), so the owner can run `/doc-check`
   - then wait for the owner's "commit" and "go"

**Rules for every phase:**
- **DONE WHEN belongs to the owner.** A check changes only when the owner says so. If a check turns out wrong or impossible, stop and ask.
- **Evidence:**
  - screenshots go in `shots/p<NN>-<screen>-<state>[-xl].png` (not in git), each with DESIGN §10's 16 answers in PROGRESS
  - command output goes in PROGRESS, trimmed to the lines that prove the check
  - the commands are CLAUDE.md's, and EDGE-CASES §7's
- **Spikes** run on a throwaway branch, `spike/<name>`. Only the written answer is kept, in the doc it affects. Code kept by accident is a bug. The spike branch stays local, never pushed, and is deleted once its phase merges.
- **Branches and merges** (as practised since Phase 1, written down 2026-11-25):
  - each phase gets a branch, `phase-N-<name>`, cut from `main`
  - if `main` lacks an unmerged decision the phase needs, the branch is cut from the branch that holds it, and they merge in order (Phase 4 did this)
  - the phase branch is pushed after each commit, so a new session can resume from GitHub
  - a phase merges only on the owner's word, by fast-forwarding `main` to the branch tip, or with a merge commit when `main` has moved, as GitHub's pull requests do (PR #1 merged Phase 3)
- **The doc check:** `/doc-check` runs after every third DONE phase, or when the owner asks. The first ran on 2026-11-25 at the owner's request, after Phases 3 and 4; the next is due after Phase 7, and step 8's report says so.
- **Owner phases:** the agent prepares checklists and templates; the owner acts; the owner's confirmation is the evidence. Keys, accounts and logins stay the owner's (CLAUDE.md).
- **Free tools only.** A download (a font, a library, a system image) needs the owner's yes first. Anything paid stops the build.
- **The save format:** `formatVersion` stays at 1, and its shape may change freely, until the first closed-test build (Phase 30). The owner's own internal-track save may be reset before then. From Phase 30 on, every change needs a migration and a fixture (TRD §6.3).
- **Gates stop the plan:**
  - **Phase 1** (the waitlist test): stop ends it; the middle band builds only through Phase 12, then the Business verdict is re-run. **Changed 2026-11-25 by the owner (PRD Q1):** the build starts before day 7, so Phases 2–5 don't wait for Phase 1. The thresholds don't move; on day 7 they apply to the build in progress.
  - **Phase 12** (G1): failure means reworking the haggle at most twice, then stopping.
  - **Phase 31** (G2) and **Phase 33** (G3) decide what follows.

**Size: v1 genuinely needs 32 phases, not about 12.**

| Kind | Count | What |
|---|---|---|
| Build phases | 20 | About one sitting each. They match the Business verdict's 17 build slices, split finer |
| Spikes | 7 | Each answers one or more of LLD's unknowns before the phase that needs it |
| Owner and waiting phases | 5 | The waitlist test, long-lead setup, G1, the 14-day closed test, the production review |

**What could be cut without breaking the product, in order:**
1. **The deal card** (half of Phase 21): the PRD names it the first cut (F12).
2. **The haggle lab's on-phone screen** (part of Phase 11): tune from the simulator's reports instead.
3. **Today's special** (part of Phase 20): one of seven retention hooks.

**Cutting more would break the product or the law.** The auction is half the stock loop. The kanakku is the day's end. Settings, reports and Start over are compliance (CR15, F17, F19). The three voices are the USP. The spikes guard the save and the screen.

---

## Milestones

| Group | Phases | Build sessions, estimated | Calendar waits | Ends with |
|---|---|---|---|---|
| **Before M1** — the go decision | 1–2 | 0: owner work | 7 days (the waitlist test) | Go, G1 only, or stop |
| **M1** — the core loop: haggle after haggle, saved; then G1 | 3–12 | **9** | About a week to run G1 with 5 people | A haggle loop on the phone, judged at G1 |
| **M2** — every v1 feature | 13–29 | **17** | — | Every v1 screen and feature, on the phone |
| **M3** — ready for strangers | 30–32 | **5** (3 phases, plus fixes during the test) | 14 days (closed test) + up to 7 days (production review) | Public in India |
| After M3 — money, behind gates | 33–42 | 4 for purchases; 4 for ads, only if they ever ship | 30 days (G3); the advisers' answers | Purchases; ads only past ~3,000 installs a month |

**v1 is about 31 build sessions**, against the Business verdict's 34 (17 slices × 2). The spikes add about 5; finer phases absorb the review sessions. At ~2.5 sessions a week (5 hours), that's about **12–13 weeks of building, plus about 4 weeks of waiting** (the test, G1, the closed test and the review). CURRENT STATE tracks sessions against these numbers. **A milestone that slips by half is a signal to re-plan, not to push.**

---

# Before M1 — the go decision

## Phase 1 — The waitlist test

STATUS: IN PROGRESS

GOAL: Know whether to build at all: the day-7 count of unique sign-ups against go (≥ 1,600) and stop (< 370).

BUILD:
- **Owner:**
  - ~~make the game's Gmail (IDEA H2-11)~~: not needed. The contact address is the owner's own Gmail (PRD CR12, decided in this phase)
  - make the Google Form exactly as `site/README.md` says, including "View results summary: off" and no "submit another response" link
  - send the form link and the game's email
- **Agent:** fill `CONFIG` in `site/bazaar-boss/index.html`, then check the page at 360 px.
- **Owner:**
  - publish the `bazaar-boss` folder to the `rameshkumark24.github.io` repo
  - record the 20–30 s Tanglish clip; post it on your own account, and once in each of 3 communities
- **Day 7:** count the raw and unique emails (`=COUNTUNIQUE(ARRAYFORMULA(LOWER(TRIM(B2:B))))`), note each post's views, and record both in PRD §12.1.

OUT OF SCOPE: Any game code (it's built in its own phases, from Phase 3); paid promotion; moving the thresholds after seeing the count.

EDGE CASES: MS-1 (duplicates and bots), OP-3 (the form's results summary), AB-6 (removal requests checked by reply).

TESTS: None: there's no code. The live page is checked by eye on a phone.

READS: IDEA → Business verdict §7; PRD §12.1 and Q1; `site/README.md`; EDGE-CASES MS-1, OP-3.

DONE WHEN:
1. USER — The live page opens on a phone, the haggle plays in all three voices, and "Join the waitlist" opens the form → phone screenshots of the page and the form.
2. PRODUCT — The form's settings match `site/README.md` → screenshots of its Responses and Presentation settings.
3. PRODUCT — Day 7's raw and unique counts, each post's views, and the decision (go / G1 only / stop) are in PRD §12.1 → the PRD change.

ASK FIRST IF: The form demands a sign-in to submit; anything costs money; the unique count lands within 10% of a threshold. The decision is then the owner's, by the verdict's rule.

DEPENDS ON: None.

PLAN MODE: No: no code. The form collects emails, so it follows `site/README.md` exactly.

PROGRESS:
- **Plan** (2026-11-25; plan mode not needed):
  1. Agent: bring `site/README.md`'s day-7 count into line with PRD §12.1, counting unique emails. **Done.**
  2. The game's email and the Google Form. **Done:**
     - the owner's own Gmail is the contact address; the decision is in PRD CR12 and §8.5
     - no connector can make Google Forms, so at the owner's request the agent built the form in the owner's Chrome with Claude in Chrome, exactly as `site/README.md` says
  3. Agent: publish the form (with the owner's OK), copy its shortened responder link, fill `formUrl`, render the page at 360 px, commit. **Done.**
  4. Publish the `bazaar-boss` folder: **done by the agent, at the owner's request.** Owner: check the live page on your phone (DONE WHEN 1). **Done, except the three voices.**
  5. Owner: record and post the clip, and send the date of the first post; the seven days start then. On day 7, send the counts and each post's views. Agent: record them, with the decision, in PRD §12.1 (DONE WHEN 3). **Waiting.**
- **Done:**
  - branch `phase-1-waitlist-test`, cut from `main` at `e406a8d`
  - the README's day-7 step now uses unique emails and the bot flag
  - the page's share tags checked: `og:url` and `og:image` are absolute, so WhatsApp can show the picture
  - `contactEmail` filled in the page's `CONFIG`
  - the Google Form "Bazaar Boss: launch waitlist", in the owner's Drive: its title, description, two questions and settings, each checked in the editor and in the responder preview
  - **the form, published** 2026-11-25 with the owner's OK (`shots/p01-form-live.jpg`). Its shortened link, `https://forms.gle/KGxi4apVkGwpkabMA`, is the page's `formUrl`
  - **the page, published** 2026-11-25: commit `ad8bf3b` in `rameshkumark24.github.io` adds only `bazaar-boss/index.html` and `bazaar-boss/og.png`; the repo's other files are untouched. Live at https://rameshkumark24.github.io/bazaar-boss/ within a minute of the push
- **Checks so far:**
  - DONE WHEN 1: **PARTLY CONFIRMED.** The owner confirmed in chat on 2026-11-25 that the page opens on their phone and "Join the waitlist" opens the form. Not confirmed yet: the haggle in all three voices on the phone. No screenshots were sent; the owner's word stands in for them. What the agent checked from the PC passes:
    - the live page returns HTTP 200 and is byte-identical to `site/bazaar-boss/index.html` (SHA-256 `b721bd1f…`, line endings normalised); `og.png` returns 200 `image/png` and is byte-identical (`48ed8fd5…`)
    - `forms.gle/KGxi4apVkGwpkabMA` lands on the form's `viewform` page, which shows its title and both questions and isn't closed to responses
    - the live page at 360 px in headless Edge: `shots/p01-page-360-live.png`. "Join the waitlist" is enabled, which happens only when `formUrl` is a Google Forms link
  - DONE WHEN 2: **PASS**, the settings match `site/README.md`:
    - `shots/p01-form-responses-settings.jpg`: email by responder input; no copy to responders; no editing; "Limit to 1 response" off
    - `shots/p01-form-presentation-settings.jpg`: the confirmation message; no "submit another response" link; no results summary
    - the responder's view: `shots/p01-form-preview-top.jpg` and `shots/p01-form-preview-bottom.jpg`
  - DONE WHEN 3: waits for day 7
- **Left:** the three voices on the phone (step 4); the clip, the posts and day 7 (step 5). **Blocked on the owner.**

## Phase 2 — Long-lead setup

STATUS: IN PROGRESS

GOAL: Every account, key, person and phone that takes calendar time is ready before a phase has to wait for it.

BUILD (owner; the agent writes each checklist and the exact commands):
- 2-step verification on the Google account and on GitHub (CR11, H2-12).
- A private requests-log sheet (H2-10). No separate Gmail: the contact address is the owner's own (PRD CR12, decided in Phase 1).
- **The upload key:** the agent gives the `keytool` command, and the owner runs it. The keystore and its passwords go in a password manager plus an offline copy, never in git. Phase 7 needs it.
- **The Play Console app entry:** "Bazaar Boss: Haggle Tycoon", a free game. Phase 7 needs it.
- **People:**
  - 16–18 closed testers whose phones run Android 7 or later, each confirming the Google account on their phone (ST-1)
  - a native Hindi reader for the Hinglish lines (Q2, CR18)
  - 5 G1 playtesters, booked for after Phase 11
- **A borrowed 2–3 GB RAM phone** (Q13). Phases 10, 16 and 29 use it.
- **Answers to the downloads the plan needs:**
  - Roboto, Rozha One and Material Symbols (Google Fonts and Google; OFL and Apache)
  - gdx-freetype and gdx-tools (Maven)
  - an API 24 emulator image
  - CC0 sound packs, or jsfxr in the browser

OUT OF SCOPE: Anything paid; the CA or lawyer (Phase 34, after the public release).

EDGE CASES: ST-1 (testers), OP-4 (key backup), AB-5 (GitHub 2-step), BR-5 (the owner's phone holds Play and debug builds side by side).

TESTS: None.

READS: PRD CR11, CR12, CR17, Q2, Q13; IDEA H2-10 to H2-12; EDGE-CASES ST-1, OP-4.

DONE WHEN:
1. PRODUCT — 2-step verification is on for Google and GitHub → the owner confirms in chat. No codes or screenshots of codes.
2. PRODUCT — The requests log exists, and the contact address is the one in PRD CR12 → the owner confirms. *(Reworded by the owner on 2026-11-25: the contact address is the owner's own Gmail.)*
3. PRODUCT — The upload keystore exists outside git, backed up twice → the owner confirms; `git status` shows no `.jks` or `.keystore`.
4. PRODUCT — The Play Console app entry exists → a screenshot of its dashboard.
5. PRODUCT — Testers (count, and each one's Android version), the Hinglish reader and the 5 G1 playtesters are lined up → counts in PROGRESS. No names in git.
6. PRODUCT — A 2–3 GB phone is available → its model and RAM in PROGRESS.
7. PRODUCT — Each download is answered yes or no → recorded in PROGRESS.

ASK FIRST IF: Anything costs money; a tester's phone is older than Android 7; Play Console asks for a verification step that isn't done yet.

DEPENDS ON: Phase 1 says go or G1 only.

PLAN MODE: No: no code. Keys and accounts are the owner's.

PROGRESS:
- **Started 2026-11-25,** when the owner chose to build before day 7 (PRD Q1). It runs alongside the spikes, Phases 3–5.
- **The owner's checklist.** The agent's part is this list and its commands:
  1. **2-step verification** on Google (myaccount.google.com → Security → 2-Step Verification) and on GitHub (Settings → Password and authentication). Say "done" in chat. Never send codes.
  2. **The requests log:** a private Google Sheet with four columns: Date in · Request · Acknowledged · Done. A row is deleted 12 months after Done (CR12). The agent can make it in the owner's Drive, if asked.
  3. **The upload key.** In Git Bash, outside the repo:
     ```bash
     mkdir -p ~/keys && keytool -genkeypair -v -storetype PKCS12 -keystore ~/keys/bazaarboss-upload.jks -alias upload -keyalg RSA -keysize 4096 -validity 10000
     ```
     - It asks for a password, twice, then a name and an organisation. Any answers work; they aren't shown to players.
     - Put the password in a password manager.
     - Copy the `.jks` file to two places off this PC, for example a USB drive and your own cloud drive.
     - Never put it in the repo; `.gitignore` already blocks `*.jks` and `*.keystore`.
  4. **The Play Console app entry:** Play Console → Create app.
     - Name: "Bazaar Boss: Haggle Tycoon"; language: English (India), if offered; Game; Free.
     - Read and tick the declarations, then create the app.
     - Send a screenshot of the new app's dashboard.
  5. **People.** For each tester, kept privately, never in git:
     - their phone's Android version (Settings → About phone), which must be 7 or later
     - the Google account on that phone (ST-1)
     - then choose the 5 G1 playtesters, and the native Hindi speaker who reads the Hinglish lines (Q2)
  6. **A 2–3 GB RAM phone** to borrow: its model and RAM.
  7. **Downloads, yes or no to each:**
     - for Phase 3: Roboto and Rozha One (Google Fonts, OFL), and gdx-freetype (libGDX, Apache 2.0, from Maven Central)
     - later: Material Symbols (Apache 2.0), which Phase 6 needs for its icon atlas (LLD U20); gdx-tools, which may not be needed now that the font baker writes its own files (TD-36; Phase 6 decides); an Android 7 (API 24) emulator image (Phase 28); CC0 sounds or jsfxr (Phase 23)
- **Done:**
  - **People:** 20 people are ready to play (the owner, 2026-11-25). That's enough for 16–18 closed testers plus the 5 G1 playtesters, who can be among them. Their Android versions aren't checked yet.
  - **Item 7, in part:** yes to Roboto, Rozha One and gdx-freetype (the owner, 2026-11-25), so Phase 3 can start. Material Symbols, gdx-tools, the API 24 image and the sounds aren't answered yet.
  - **Item 7, more:** yes to Material Symbols (the owner, 2026-11-26), for Phase 6's icon atlas (LLD U20). gdx-tools, the API 24 image and the sounds aren't answered yet.
- **Left:** items 1–6, apart from the head count; the rest of item 7.

---

# M1 — The core loop: haggle after haggle, saved; then G1

## Phase 3 — Spike: text on screen

STATUS: DONE

GOAL: Know how text will be drawn: baked bitmap fonts or distance-field fonts, crisp at XL on a 420-dpi screen, with ₹ in both faces. Also know whether Android writes non-Latin digits under Marathi (LLD U3, U9).

BUILD (on branch `spike/text`, thrown away):
- Bake Roboto and Rozha One with gdx-freetype at each text-size step.
- Draw one test screen: "₹1,25,000", "Annoyed. You barely moved.", curly quotes, the minus sign. Screenshot it at S, M, L and XL at 420 dpi, and at `wm density 546` (316 dp).
- Compare a distance-field version.
- Print `String.format` under `mr-IN` on the device.
- Write the answers into LLD §10, and into TRD TD-26 if the font method changes.

OUT OF SCOPE: The real font pipeline and its full character set (Phase 6); the icon atlas; any screen.

EDGE CASES: DV-1 (digits), DV-4 (font atlases at most 2,048 px), CL-3 (formats).

TESTS: None kept: it's a spike.

READS: DESIGN §2.2, §2.5; TRD §1.2 (fonts and icons), TD-26; LLD §10 U3, U9.

DONE WHEN:
1. LLD §10's U3 and U9 rows carry a dated answer with its evidence: `shots/p03-text-{s,m,l,xl}.png` at 420 dpi and at 316 dp; the digits the phone printed under `mr-IN`; the chosen font method, recorded in a TRD entry that corrects TD-26, if it changed. *(Reworded by the owner on 2026-11-26, as the code review recommended: the decision log is never overwritten.)*

ASK FIRST IF: The fonts or gdx-freetype aren't approved yet (Phase 2); distance-field fonts need a new dependency.

DEPENDS ON: Phase 1 (go or G1 only); Phase 2's answer on the font downloads.

PLAN MODE: No: throwaway code.

PROGRESS:
- **Started 2026-11-25.**
  - The answers go on branch `phase-3-text-spike`, cut from `main` at `aeb69a3`.
  - The throwaway code goes on `spike/text`, which stays local.
  - The owner said yes to the downloads on 2026-11-25: Roboto, Rozha One and gdx-freetype.
- **A gap in the docs that this spike has to fill:** DESIGN §2.2 and TRD §1.2 say the fonts are "baked at build time, per text-size step", but not at which screen density. A build can't know the phone's density, and "Display size" changes it too.
- **Plan:**
  1. Download the fonts from Google's own sources into a new folder, check that they're TrueType, and note their SHA-256. gdx-freetype comes through Gradle.
  2. On `spike/text`, one test screen per text-size step (from Android's font scale, as DESIGN §2.2 says), drawn three ways:
     - **A:** baked at this phone's exact pixel size: what a build could only do per density, or FreeType at runtime (which L-11 rules out)
     - **B:** baked once at density 4.0 and scaled down with mipmaps: one realistic build-time bake
     - **C:** a distance-field font, one atlas per face, generated in the spike (no new library)
     - each way shows Rozha One `display` in curly quotes with ₹, a Roboto 500 `price` "₹1,25,000" with "−10%", `body` "Annoyed. You barely moved." and `caption`
  3. Log:
     - which glyphs each face has: ₹, curly quotes, the minus sign, the en dash, the ellipsis
     - whether Roboto's digits are tabular
     - each way's time to make its fonts, and its atlas sizes against the 2,048 px limit (DV-4)
     - U9: `String.format` and `NumberFormat` under `mr-IN` and other locales, explicit and as the app's language; and JDK 17 for comparison
  4. Screenshots at S, M, L and XL at 420 dpi, and again at `wm density 546` (316 dp), plus zoomed crops to compare the three ways.
  5. Write the answers into LLD §10 (U3, U9), and into the TRD if the font method changes.
- **Done:** steps 1–5. Tests ran on the `Medium_Phone_API_36.1` emulator (Android 16, 420 dpi) and in the desktop window (JDK 17). Every emulator setting was put back: font scale 1.0, density 420, no app language.
- **The downloads,** from Google's own sources (the owner said yes):
  - **Roboto 400 and 500:** the static files the Google Fonts API serves (v51).
    - 400: SHA-256 `dece7c71bbe61710787f0839c722c65e87355dd2eb4a823e6b5488121ff81e4b`
    - 500: SHA-256 `4d984240cefb093a74300bcae4b5dbd49f700e0af501eaaa079c56aa64142d2f`
  - **Rozha One:** `RozhaOne-Regular.ttf` from `github.com/google/fonts`, SHA-256 `8491b838c31f30272aa6da67e4749afabab8297db82a055fd2d0aa689f500ee3`.
  - All three are TrueType under the SIL OFL, and the licence texts came with them.
  - **gdx-freetype 1.14.2** and its natives came through Gradle.
- **What the spike found:**
  - **Glyphs:** all three faces have ₹, “ ” ‘ ’, the en dash, the minus sign and the ellipsis. Roboto 500's digits are tabular: the advances were 20 px at 36 px, 54 at 95, and 98 at 172.
  - **A, bitmaps for the exact pixel size:** crisp everywhere. Making them on the phone took 0.3–0.5 s warm (1.8 s cold), and 10–16 MB of atlases per step.
  - **B, bitmaps baked once at density 4.0 and scaled:** softer, with thin lines bleeding from neighbouring glyphs ("left", "it", "late", and before "Okay"). It took 16–20 MB of atlases per step.
  - **C, distance fields:** close to A at S–XL, at 420 dpi, 316 dp and 280 dpi, from three 1024 px atlases of about 75 KB each as PNG.
    - Making them took 0.4 s on the PC but 3.3 s warm on the emulator, so it's a build step.
    - Magnified 2.7× (the price at XL on 316 dp), the 64 px base shows notched edges. A 128 px base is smooth (`shots/p03-text-zoom-base64-vs-128.png`) and costs 215 KB as PNG.
  - **U9:** as LLD §10 records. Android writes non-Latin digits under `mr-IN` and other locales, also as the app's language, and its `NumberFormat` groups the Indian way, unlike JDK 17.
- **The decision:** distance-field atlases baked at build time (TRD TD-36). TD-26 already said to switch "if baked bitmaps look soft". TD-26 itself stays as written, because the decision log only grows, so TD-36 corrects it, as TD-35 did for TD-20. DESIGN §2.2, TRD §0 and TRD §1.2 now point to TD-36.
- **For Phase 6 and later:**
  - **The font baker in `tools`:**
    - TD-36's base sizes, checked with the zoom test
    - FreeType's padding, with each glyph moved back by it, because gdx-freetype pads the bitmap but leaves the offsets at the ink
    - one-channel textures
  - **Drawing:** scene2d labels need the distance-field shader while they draw. That means a label that swaps the batch's shader, at the cost of one batch flush per swap.
  - **Android natives:** the build's natives task keeps only the first natives jar per ABI. That's fine while FreeType stays at build time. If a second native library ever ships, copy every matching jar in one `fs.sync` (KNOWN ISSUES K2).
  - **Icons:** Material Symbols is a font, so the icon atlas can use the same baker.
- **DONE WHEN 1: NOT VERIFIED as written; waiting on the owner** (found in the code review, 2026-11-25). The check says the chosen method is "copied into TRD TD-26", but the TRD's decision log never overwrites an entry, so the method went into TD-36, which corrects TD-26. Meeting a check in a way other than as it's written is the owner's call, so the phase isn't DONE until the owner accepts it (CURRENT STATE). Everything else the check names is here: LLD §10's U3 and U9 rows carry dated answers, with this evidence:
  - **The four steps at 420 dpi:** `shots/p03-text-s.png`, `-m.png`, `-l.png`, `-xl.png`
  - **The four steps at 316 dp:** `shots/p03-text-s-316dp.png` … `-xl-316dp.png`, plus `-xl-316dp-c.png`, which shows C on its own because three blocks overflow at XL
  - **Zoomed crops:** `shots/p03-text-zoom-s-caption.png`, `-zoom-xl-display.png`, `-zoom-s-280dpi-caption.png`, `-zoom-xl-316dp-price.png`, `-zoom-base64-vs-128.png`, with `p03-text-s-280dpi.png`
  - **The digits the phone printed under `mr-IN`:** the logcat lines in LLD §10 U9, plus `shots/p03-text-mr-IN.png` and `-mr-IN-header.png`
  - **The chosen method:** TRD TD-36
- **Handoff, 2026-11-25** (the session stopped here at the owner's request):
  - **Done:** the whole phase. Its answers are committed and pushed on `phase-3-text-spike`: LLD §10 U3 and U9; TRD TD-36, §0 and §1.2; DESIGN §2.2's font sentence; EDGE-CASES DV-1.
  - **Half-finished:** nothing.
    - Nothing uncommitted belongs to the work. The untracked `.vscode/settings.json` was made by VS Code's Java tooling; leave it out of git.
    - The screenshots live only in `shots/` on this PC, which git ignores.
    - The emulator was stopped with every setting put back.
  - **Left, in order:**
    1. **The owner** reviews `phase-3-text-spike` and says whether to merge. It fast-forwards `main` from `aeb69a3`, as Phases 1 and 4 did.
    2. **The agent**, on the owner's "merge": fast-forward `main`, push it, and delete the local throwaway branches `spike/text` and `spike/df-smoothing` (the code review's test). Their code was never pushed and isn't needed; the answers are in the docs.
    3. **The next phase is Phase 5,** the save spike (CURRENT STATE). Cut its branch from the updated `main`.
  - **Waiting on the owner, so not in any doc as a decision yet:**
    - **DESIGN §2.2:** the agent changed one sentence to point at TD-36 ("baked to bitmaps" became "distance-field atlases"). The faces, sizes and steps are unchanged. The owner was asked whether that edit is all right; if not, revert that line and keep TD-36 in the TRD only.
    - **Phase 1 and Phase 2 items:** whether the waitlist haggle played in all three voices on the owner's phone (Phase 1, DONE WHEN 1), and the owner's Phase 2 list. Both are in their phases' PROGRESS and in CURRENT STATE.
- **Code review, 2026-11-25:** DONE WHEN 1 is reopened (above), so the phase is IN PROGRESS again. Nothing is left to build: it waits on the owner's word on DONE WHEN 1, then its review and merge. The review also added TRD TD-37 (how distance-field text sets its smoothing), with its evidence in LLD §10 U3.
- **DONE, 2026-11-26.** The owner accepted the code review's recommendation and reworded DONE WHEN 1. TD-36 is that TRD entry, so **DONE WHEN 1: PASS**, with the evidence above. Its review is done and PR #1 merged it; the handoff note merges with the doc check's branch.

## Phase 4 — Spike: the Android 16 shell

STATUS: DONE

GOAL: Know how libGDX 1.14.2 handles four things on Android 16: back, the safe area, drawing only on demand, and the desktop window (LLD U1, U2, U4, U19).

BUILD (on branch `spike/shell`, thrown away):
- A two-screen scene2d prototype in which back opens a pause card, tested in gesture and 3-button navigation.
- A layout bound to the insets, with and without a cutout.
- Non-continuous rendering, with a running action and input.
- `./gradlew :lwjgl3:run`.
- Answers go into LLD §10, TRD TD-20 and TD-25 if needed, and CLAUDE.md if the desktop window works.

OUT OF SCOPE: The real app shell (Phase 6); predictive-back animations.

EDGE CASES: DV-3 (system bars), IN-4 (the back swipe and the slider), DV-5 (drawing on demand).

TESTS: None kept.

READS: TRD TD-20, TD-25, §0.4; DESIGN §2.6, §4; LLD §10 U1, U2, U4, U19; EDGE-CASES §7 (the navigation-mode commands).

DONE WHEN:
1. LLD §10's U1, U2, U4 and U19 rows carry dated answers with their evidence:
   - `shots/p04-shell-*.png` in both navigation modes, showing back opening the pause card and the layout inside the insets
   - frames drawn per second, idle and while animating
   - the output of `:lwjgl3:run`
   - CLAUDE.md's verified table updated if `:lwjgl3:run` works

ASK FIRST IF: Back needs an AndroidX Activity dependency or a libGDX upgrade, either of which is a new download.

DEPENDS ON: Phase 1.

PLAN MODE: No.

PROGRESS:
- **Started 2026-11-25.**
  - The answers go on branch `phase-4-android-shell`. It's cut from `phase-1-waitlist-test`, not `main`, because `main` doesn't have the build-now decision (PRD Q1) yet, so Phase 1's branch merges first.
  - The throwaway code goes on `spike/shell`, which stays local.
- **Plan:**
  1. Read libGDX 1.14.2's Android backend, already in Gradle's cache, for back and insets. **Done**, with `javap`:
     - **Back:** on Android 13+ (API 33), `setCatchKey(BACK, true)` registers an `OnBackInvokedCallback`, and `false` removes it. The callback queues a key-down only (no key-up), and **it doesn't ask for a frame**. Touches, ordinary key events and `postRunnable` do ask for one.
     - **Insets:** `getSafeInset*()` reads only the display cutout (API 28+), never the status or navigation bars.
  2. On `spike/shell`, a two-screen scene2d prototype:
     - The Stall opens the Haggle. In the Haggle, back opens a pause card, and back again closes it. The Stall doesn't catch back, so Android takes it.
     - Drawing only on demand, with frames counted and logged each second.
     - `android` reads the window insets and passes them to `core`. Both sets are drawn on screen, and the layout can switch between them.
     - Two back routes: libGDX's own, and one through the launcher.
  3. On the Android 16 emulator, in gesture and 3-button navigation, with and without a cutout:
     - screenshots
     - back by key and by edge swipe
     - frames per second, idle and while animating
     - back while nothing is drawing
  4. `./gradlew :lwjgl3:run --offline`.
  5. Write the answers into LLD §10 (U1, U2, U4, U19), into TRD TD-20 and TD-25 if they change, and into CLAUDE.md if the desktop window works.
- **Done:** steps 1–5. Tests ran on the `Medium_Phone_API_36.1` emulator (Android 16, 1080 × 2400, 420 dpi), on `LowEnd_A9` (Android 9), and in the desktop window. Every emulator setting was put back: 3-button navigation, no cutout.
- **What the spike found:**
  - **Back with libGDX's own route (`setCatchKey`), Android 16:**
    - the 3-button bar's back reached the game 9 s late, only when the screen was next touched
    - a gesture back reached it 3 s late, the same way
    - an injected `input keyevent 4` arrived twice, so the card opened and closed in 14 ms
  - **Back with the launcher's own callback, Android 16:** both modes opened and closed the card, reaching the game within 1 ms each time. Only the injected key still arrived twice, hence TD-35's rule: `core` ignores BACK key events on Android 13+.
  - **Back on Android 9:** libGDX's route is right there: one key-down and one key-up per press, with no stall.
  - **Insets on Android 16:**

    | Setup | Android's insets for the view | libGDX's safe insets |
    |---|---|---|
    | 3-button | top 63, bottom 126 px | all 0 |
    | Gesture | top 63, bottom 63 px | all 0 |
    | Gesture with a punch-hole cutout, cold start | top 136, bottom 63 px | top 136, bottom 0 |

    Inside libGDX's insets, the title and Mute sit under the status bar, and the bottom button under the 3-button bar.
  - **Insets on Android 9:** the surface is 720 × 1136, between the bars, and the view gets no insets. A padding of 0 is correct there.
  - **Drawing on demand:** 0 frames a second when idle; 180 frames in the 3 s animation; 15–18 for each 250 ms card.
  - **The Stall doesn't catch back:** Android took it and showed the home screen.
  - **Desktop:** `./gradlew --offline :lwjgl3:run` was `BUILD SUCCESSFUL` in 8 s.
  - **For Phase 5 (U7):** a navigation-mode or cutout change recreates the activity, and libGDX didn't dispose the old game. Its timer thread kept logging.
  - **For Phase 6 (IN-4):** the back-gesture zone is 30 dp (`config_backGestureInset`), so DESIGN §2.7's 24 dp slider margin is too small. Raised with the owner.
- **Where the answers went:** LLD §10 (U1, U2, U4, U19, plus a lead on U7) and `PlatformServices` in LLD §4; TRD TD-35, which corrects TD-20; CLAUDE.md's verified table; EDGE-CASES IN-4 and §7.
- **DONE WHEN 1: PASS.** LLD §10's U1, U2, U4 and U19 rows carry dated answers, with this evidence:
  - **3-button navigation:**
    - `shots/p04-shell-3button-stall.png`: the layout inside Android's insets
    - `shots/p04-shell-3button-stall-gdxinsets.png`: the failure inside libGDX's insets
    - `shots/p04-shell-3button-haggle-navback-launcher.png`: back opened the pause card
    - libGDX's route: `…-back-idle-libgdx.png`, `…-navback-libgdx.png`, `…-navback-libgdx-aftertap.png`
  - **Gesture navigation:**
    - `shots/p04-shell-gesture-haggle.png`: the layout
    - `shots/p04-shell-gesture-haggle-swipe-launcher.png`: back opened the pause card
    - `…-swipe-libgdx.png`, `…-card.png`, `…-animating.png`, `…-stall-back-home.png`
  - **The cutout:** `shots/p04-shell-gesture-cutout-hole-stall.png` and `…-cold.png`.
  - **Android 9:** `shots/p04-shell-android9-stall.png`, `…-haggle-back.png`.
  - **Frames a second:** the logcat lines above, from the spike's once-a-second counter.
  - **Desktop:** the output of `:lwjgl3:run` and `shots/p04-shell-desktop.png`. CLAUDE.md's verified table now has `:lwjgl3:run`.
- **Checks on this branch:** `./gradlew --offline check` → `BUILD SUCCESSFUL in 12s`. The branch holds no spike code; that stays on the local branch `spike/shell`, to be deleted after the review.

## Phase 5 — Spike: the save on a cheap phone

STATUS: DONE

GOAL: Know the save's costs and risks before building it (LLD U5–U8):
- U5: how fast libGDX's JSON reader and writer are with literal keys, and whether longs stay exact
- U6: the commit's p95 time on LowEnd_A9
- U7: the order of events when the activity is recreated
- U8: whether a fixture can be installed through `run-as`

BUILD (on branch `spike/save`, thrown away):
- A synthetic 50 KB state and a 0.5 MB pack; time encoding and decoding on LowEnd_A9.
- Time write, fsync and rename, 200 times; report p50 and p95.
- Change the font scale while a write is pending, and log dispose, drain and GL-context events. Also switch the navigation mode, which no `configChanges` value absorbs. First settle whether `dispose()` runs on a recreation at all: Phase 4 saw only `pause()` (EDGE-CASES LC-2).
- Copy a fixture with `run-as` from `/data/local/tmp`.
- Write the answers into LLD §10, TRD §0.4 and §4.10, and EDGE-CASES LC-2 and T-3. If the drain moves out of `dispose()`, a new TRD entry corrects TD-28. Add the fixture commands to CLAUDE.md if U8 works.

OUT OF SCOPE: The real codec and writer (Phase 6); backups and repairs (Phase 13).

EDGE CASES: SV-4 (a partial write), LC-2 (recreation), LC-5 (the GL context), HO-3 (a slow fsync), DV-4 (cold start).

TESTS: None kept.

READS: TRD §0.3, §0.4, §4.5, §4.10; LLD §3.3, §6 rows 1–2, §10 U5–U8; EDGE-CASES §7.

DONE WHEN:
1. LLD §10's U5–U8 rows carry dated answers with their evidence: encode and decode times for 50 KB and the pack, on LowEnd_A9; commit p50 and p95; the recreation sequence, as logcat lines; the fixture commands and their output, copied into CLAUDE.md if they work.

ASK FIRST IF: The commit's p95 is over 100 ms, which would break the tap-to-reaction budget; the design then changes before Phase 6.

DEPENDS ON: Phase 1.

PLAN MODE: No.

PROGRESS:
- **Started 2026-11-25.**
  - The answers go on branch `phase-5-save-spike`. It's cut from `doc-check-2026-11-25` at `385a955` (the doc check and its code review), because this phase's build list and LC-2's note live there and not yet in `main`. So it merges after that branch.
  - The throwaway code goes on `spike/save`, which stays local.
  - No downloads: libGDX's JSON classes are in `gdx` core, already in Gradle's cache.
- **Plan:**
  1. **U5:** a synthetic `GameState`, shaped like TRD §4.5, at about 15 KB (typical) and 50 KB (worst), plus a synthetic 0.5 MB pack.
     - Encode with `JsonWriter`, every key a literal, plus CRC32 and the `BBSAVE` header; decode with `JsonReader`, walking every field.
     - Time encode and decode on `LowEnd_A9` (Android 9, 2 GB), with warm-up, as p50 and p95.
     - Round-trip extreme longs: ±2⁶³, 2⁵³ + 1, and an RNG state.
  2. **U6:** on a writer thread, 200 commits each of 15 KB and 50 KB: write the `.tmp` file, `getFD().sync()`, close, `renameTo`, with the result checked. p50, p95 and maximum on `LowEnd_A9`, and on the Android 16 emulator for comparison. A real cheap phone waits for Phase 2's borrowed phone.
  3. **U7:** log the order of events through a rebuild:
     - the activity's lifecycle, with `isFinishing` and `isChangingConfigurations`
     - the game's `create`, `pause`, `resume` and `dispose`, with an instance number
     - the EGL context's handle
     - a process-wide writer holding a deliberately slow pending write (1.5 s)
     - triggered by a font-scale change, a navigation-mode switch, going home followed by `am kill`, and back on the root screen
     - the question: is `dispose()` called, and does the new game load the pending write?
  4. **U8:** push a valid fixture (`BBSAVE` header, CRC and length) to `/data/local/tmp`, copy it into `files/save/` with `run-as`, relaunch, and log what loads. On Android 16 and Android 9.
  5. **Write the answers** into LLD §10 (U5–U8), TRD §0.4 and §4.10, and EDGE-CASES LC-2 and T-3; add a new TRD entry correcting TD-28 if the drain moves; and add the fixture commands to CLAUDE.md if U8 works.
- **Done:** steps 1–5.
  - Tests ran on `LowEnd_A9` (Android 9, 2 GB) and `Medium_Phone_API_36.1` (Android 16), both on debug builds.
  - Every emulator setting was put back: font scale 1.0, 3-button navigation.
- **U5, on `LowEnd_A9`, warm, launched with `--ez bench true`:**
  ```
  U5 longs: 8/8 exact; text: {"v":[9223372036854775807,-9223372036854775808,9007199254740993,-9007199254740993,0,-1,1764000000123,-7046029254386353131]}
  U5 typical save, 14074 bytes: encode+CRC: p50 2.92 ms  p95 22.25 ms | check+decode: p50 2.27 ms  p95 12.00 ms
  U5 worst save, 49639 bytes: encode+CRC: p50 5.79 ms  p95 20.33 ms | check+decode: p50 5.58 ms  p95 15.75 ms
  U5 pack, 524343 bytes, 1945 entries: parse+walk: p50 30.81 ms  p95 41.36 ms
  ```
  The first run measured the typical save at encode p50 2.87 / p95 11.80 and decode p50 2.29 / p95 7.57, and the pack at p50 10.93 / p95 25.99 ms.
- **U6, on `LowEnd_A9`, 200 commits each:**
  ```
  U6 typical commit, 14064 bytes: write+fsync+rename: p50 7.54 ms  p95 11.55 ms  max 20.80 ms
  U6 worst commit, 49647 bytes: write+fsync+rename: p50 7.96 ms  p95 11.21 ms  max 18.71 ms
  ```
  Android 16 measured p95 7.9–19.8 ms, max 65 ms. **Under 100 ms, so ASK FIRST didn't trigger.**
- **Cold code, on Android 16:**
  - Just after install and boot, the 50 KB encode's p95 was 177 ms and its decode's 359 ms. A minute later they were 19 and 17 ms.
  - The first taps after a cold launch encoded in 29.6, 11.7, 9.5, 8.6 and 7.1 ms.
  - `cmd package compile -m speed` left the debug build at `status=verify`, because debuggable apps aren't compiled ahead of time.
- **U7, the order of events.** On Android 9, the font scale went to 1.3 with a 1.5 s write pending:
  ```
  game #1 tap: queued seq 1 (14056 bytes, slow: 1.5 s)
  activity #1 onPause begins (finishing=false, changingConfigurations=true)
  game #1 pause (EGL context 0x7d97d2af3b00)
  writer: seq 1 committed in 1524 ms
  game #1 pause: drained in 1273 ms (idle=true, committed up to 1)
  activity #1 onPause returned, onStop, onDestroy (changingConfigurations=true)
  activity #2 onCreate (restoring=true)
  game #2 create (EGL context 0x7d97c92eb800)
  game #2 load: nextId 1 from 14056 bytes, header and CRC ok
  ```
  - **There's no `game #1 DISPOSE` line.** Android 16 with the navigation-mode switch did the same: drained in 1006 ms, and game #2 loaded nextId 6.
  - **With no drain,** the rebuild went straight on. Game #2 "waited 974 ms for the writer before loading" and loaded nextId 2.
  - **Process death:**
    - with no drain, a 4 s write pending, then home and `am kill`: the relaunch loaded nextId 3, so seq 4 was lost
    - with the drain and a 1.5 s write: "pause: drained in 1122 ms", and the relaunch loaded nextId 4
  - **Back on the root screen:** `onPause begins (finishing=true)`, then `pause` (drained in 1139 ms), then `game #1 DISPOSE`, `onStop` and `onDestroy`.
  - **libGDX's limit:** `AndroidGraphics.pause` waits `4000l` ms, then logs "waiting for pause synchronization took too long; assuming deadlock and killing" and calls `Process.killProcess`.
- **U8, on Android 9 and 16:** `1 file pushed`, then `-rw------- 1 u0_a231 u0_a231 432 … game.json`, then `game #1 load: nextId 777 from 432 bytes, header and CRC ok`. A damaged file gave `load: REJECTED, CRC mismatch`.
- **Where the answers went:**
  - LLD §10 U5–U8, and §6 rows 1–2
  - TRD §0.4, §4.10, and TD-38, which corrects TD-28
  - EDGE-CASES LC-2 and T-3
  - CLAUDE.md's fixture row
  - Phase 6's build list: TD-38, and warming the codec
  - KNOWN ISSUES: K5 resolved
- **For Phase 6 and later:**
  - **One writer per process, and no threads or timers inside a game object.** The old game object is never disposed on a rebuild, so anything it owns outlives it.
  - **The drain in `pause()` holds Android's `onPause` while it waits:** normally milliseconds (U6), at most 2 s.
  - **Warm the codec while loading** by encoding the loaded state once.
  - **Phase 29 measures a release build on the borrowed phone.** A baseline profile may matter for the first taps.
- **DONE WHEN 1: PASS.** LLD §10's U5–U8 rows carry dated answers, with this evidence, all above:
  - the encode and decode times for 50 KB and the pack, on `LowEnd_A9`
  - the commit's p50 and p95
  - the recreation sequence, as logcat lines
  - the fixture commands and their output, now in CLAUDE.md

## Phase 6 — The vertical slice: one haggle, saved and resumed

STATUS: IN PROGRESS

GOAL: On the phone, a walk-in seller offers a brass lamp. You haggle with −10%, −25%, the slider and Offer, then Accept or Walk away. The deal result shows the verdict. Every round is saved, and after Android kills the game it resumes at the same round.

BUILD:
- **The skeleton (LLD §1, TD-34):**
  - `core` moves under `….core.*`; an `assets/` folder is wired in
  - the `devtools` module (a dev-menu stub) and the `tools` module with its guards: dependency matrix, catches, file size, Android 7 APIs, palette
  - the merged-manifest guard: the permission allowlist and the exports allowlist, including `ProfileInstallReceiver` (BR-1)
  - `.githooks/pre-push`
  - `palette.py --json`, writing `palette.json`, and `Palette.java`
  - debug builds get `applicationIdSuffix '.dev'`, with the FileProvider authority taken from `${applicationId}` (BR-5); CLAUDE.md's commands are updated to match and run again
- **`logic`:**
  - `kernel`: `Money`, `Bp`, `BazaarSteps`, `IndianNumberFormat`, `Dates`, `Clock`, `Rng`, `Outcome`, `Rejected`, `Check`
  - `content`: a hand-written pack with one item and one personality, in English
  - `haggle`: `VisitFactory` (the walk-away price fixed at creation and never below scrap), `HaggleRules.respond`, `QuickButtons` (buying), `DealResult`, `WalkedAway`
  - `player.Wallet`
  - `game`: the `Game` façade, `TurnRef`, the owed deal result, `SaveSink`
  - `persist`: `SaveFormat` v1, `SaveCodec` (header, CRC, literal keys), `SaveLoader` (game file and first launch only)
- **`core`:**
  - the shell: `BazaarBossGame`, `ScreenRouter`, `ModalQueue`, `BackDispatcher` (in the haggle, back opens Pause), `TapGuard`, `RenderOnDemand` (as Phase 4 found)
  - the components it needs: Button (with its busy state), Quick-offer chip, Offer slider (bazaar steps, − and + nudges; its target 32 dp clear of each edge, and excluded from the back gesture on Android 10+), Price tag, Speech line, Meter, Panel, Modal card, Banner
  - fonts as Phase 3 found (TD-36): the distance-field baker in `tools`, with TD-36's base sizes, the glyph-offset fix and one-channel atlases, checked on the phone with the zoom test (CLAUDE.md), including the extremes: `label` at S on 280 dpi and `price` at XL on density 4.5; and a label that draws with the distance-field shader, sets its smoothing from its size on screen, and turns integer positions off (TD-37)
  - the icon atlas from Material Symbols through the same baker (LLD U20; the download needs the owner's yes first)
  - a placeholder face (shapes plus the mood word)
  - `SaveWriter` (one per process, coalescing), `FileSaveFiles`. The new game waits for the writer before loading, and `pause()` drains it, for at most 2 s (TD-38)
  - the codec warmed while loading, so the first tap isn't paying for cold code (U5)
  - the haggle screen, the deal result card and the pause card
- **`android`:** `singleTask` and the task-root check (LC-1); the wider `configChanges` (LC-2); pause on focus loss.
  - the view's `WindowInsets` passed to `core` (TD-35)
  - back through the launcher's own callback on Android 13+, through `PlatformServices.catchBack` (TD-35); the manifest comment that still says back is "routed to libGDX" is corrected with it
  - `excludeFromBackGesture` over the slider on Android 10+ (DESIGN §2.7, IN-4)

OUT OF SCOPE:
- Other personalities, selling and walking away's call-back (Phase 9)
- The 3 voices (Phase 8); faces (Phase 11); the stall (Phase 14)
- Backups and repairs (Phase 13); Settings (Phase 7); sound (Phase 23)

EDGE CASES: SV-1, SV-2, SV-4, LC-1, LC-2, LC-3 (the deal result), IN-1, IN-3, IN-4, HE-1 (buying), DV-1, DV-3, DV-5, DC-1, HO-2, HO-3, BR-1, BR-5.

TESTS:
- **CODE:**
  - DR6, DR7, DR8, DR10 and DR12 (the slice's subset); DR1 and DR2 on `Wallet`
  - the `Money` and `IndianNumberFormat` table
  - dates and a save round trip under `mr-IN`, `bn-IN`, `ur-IN`, `ar-EG` and `tr-TR`
  - the codec round trip, plus a renamed-field check
  - every guard
- **INTEGRATION:** the writer with real files: a kill at each of the 4 commit steps; coalescing; a stale turn.
- **USER:** the haggle screen's states through fixtures (if Phase 5's U8 works) or by playing; process death, then resume.

READS: LLD §1–§4, §7.1–§7.3, §9; TRD §4.2, §4.5, §4.10, §5.2; PRD F2, DR6–DR12, F15 AC1; DESIGN §2, §4, §10; APP-FLOW §1.7, §1.8 row 3; EDGE-CASES as listed.

DONE WHEN:
1. CODE — `./gradlew check` passes → the output, listing the DR tests, the locale tests and every guard.
2. CODE — the pre-push hook blocks a push when a test fails → the hook's output, from a deliberately failing test that is then reverted.
3. INTEGRATION — the kill-point test reloads after a kill at each of the 4 commit steps, and each reload equals "before" or "after", never between → the test report.
4. INTEGRATION — on the emulator: two offers, then `am kill`, then relaunch. The same seller, offers, mood and patience come back → screenshots before and after; the save's first line, `BBSAVE <crc> <len>`, read through `run-as`.
5. USER — the haggle screen in 5 states (first offer, counter, deal result, walked away, paused), at M, and at XL on 316 dp, in both navigation modes → `shots/p06-*.png`; all 16 checklist answers yes.
6. USER — a double tap on Accept makes one deal → the save's deal count, read through `run-as`, is 1.
7. PRODUCT — F2 AC1, AC2 (no walk-away price shown), AC3, AC4, AC5, and AC10 (buying); F15 AC1 → each checked on the emulator, noted in PROGRESS.
8. PRODUCT — the empty project's commands still work with the `.dev` package (`installDebug`, `am start`, `screencap`) → CLAUDE.md updated, with each command's output.

ASK FIRST IF: A spike's answer forces a design change, such as distance-field fonts needing a new library; the save needs fields beyond LLD's; anything needs a new permission.

DEPENDS ON: Phases 3, 4 and 5; Phase 2's answer on the Material Symbols download (the icon atlas).

PLAN MODE: Yes. It lays the architecture, and the save is the player's data.

PROGRESS:
- **Started 2026-11-26** on branch `phase-6-vertical-slice`, cut from `main` at `08ddf56`. **The owner approved the plan, decisions A–F included, on 2026-11-26.**
- **Size:** about **4 sessions**, not one. It is LLD slices 1, 3, 4 and 6 in part. Seven checkpoints, each committed and pushed, so a stop anywhere loses nothing.
- **The plan, in checkpoints:**
  1. **The skeleton.**
     - `core` moves under `….core.*`; `assets/` is wired into `android` and `lwjgl3`.
     - New `devtools` and `tools` modules; `.githooks/pre-push` runs `./gradlew check` (enabled once, with `git config core.hooksPath .githooks`).
     - `palette.py --json` writes `palette.json`, and `core.ui.style.Palette` is generated from it.
     - Debug builds get the `.dev` suffix, with CLAUDE.md's commands re-run.
     - Proof: `check` passes, and the hook blocks a deliberately failing test (DONE WHEN 2, 8).
  2. **The guards in `tools`, each with a failing fixture first:**
     - the dependency matrix (LLD §2.3)
     - empty and broad catches
     - classes over 500 lines
     - Android 7 APIs
     - the palette
     - `android`'s merged-manifest check: the permission allowlist, and the exports allowlist with `ProfileInstallReceiver`
  3. **`logic`, with tests written alongside:**
     - `kernel`: `Money`, `Bp`, `BazaarSteps`, `IndianNumberFormat`, `Dates`, `Clock`, `Rng`, `Outcome`, `Rejected`, `Check`
     - `content`: the hand-written slice pack
     - `haggle`: `VisitFactory`, `HaggleRules.respond`, `QuickButtons`, `DealResult`, `WalkedAway`
     - `player.Wallet`
     - `game`: `Game`, `TurnRef`, the owed deal result, `SaveSink`
     - `persist`: `SaveFormat` v1, `SaveCodec`, `SaveLoader`
     - Tests: DR6, DR7, DR8, DR10, DR12, DR1 and DR2; the money table; the five locales; the codec round trip and the renamed-field check
  4. **Saving in `core.persist`:**
     - `SaveWriter`, one per process and coalescing; the new game waits for it before loading, and `pause()` drains it, at most 2 s (TD-38). `FileSaveFiles`.
     - The integration tests: a kill at each of the 4 commit steps, coalescing, a stale turn (DONE WHEN 3)
     - The codec is warmed while loading (U5).
  5. **Fonts and icons:**
     - **Fonts:** the distance-field baker in `tools` (TD-36): gdx-freetype at build time, the glyph-offset fix, one-channel atlases at most 2,048 px, with the base sizes TD-36 sets
     - **Icons:** the Material Symbols icon atlas through the same baker (U20)
     - **The label:** one that sets its smoothing from its size on screen, with integer positions off (TD-37)
     - **Proof:** the zoom test on the phone, at the extremes: `label` at S on 280 dpi, and `price` at XL on density 4.5
  6. **The shell and the screens:**
     - the shell: `BazaarBossGame`, `ScreenRouter`, `ModalQueue`, `BackDispatcher`, `TapGuard`, `RenderOnDemand`
     - the nine components the slice needs; the placeholder face
     - the haggle screen, the deal result card and the pause card
     - **`android`:** `singleTask` and the task-root check; the wider `configChanges`; pause on focus loss; the view's `WindowInsets`; back through the launcher's own callback (TD-35, fixing the stale manifest comment); the slider's gesture exclusion
  7. **Proof on the emulator:**
     - process death, then resume (DONE WHEN 4)
     - the 5 states at M and at XL on 316 dp, in both navigation modes, with all 16 answers (DONE WHEN 5)
     - a double tap on Accept (DONE WHEN 6)
     - F2's acceptance criteria and F15 AC1 (DONE WHEN 7)
- **Decisions the docs leave open, for the owner to approve with the plan:**
  - **A. A placeholder haggle model.** PRD Q9 leaves the numbers to tuning, so these live in the slice pack, marked as placeholders. Phases 9, 11 and 12 (G1) tune them.
    - **The seller:** one personality, the emotional walk-in seller selling a brass lamp, as on the waitlist page.
    - **The walk-away price** comes from the item's value, its condition and the personality, and is never below scrap plus a margin (DR6, DR9).
    - **The opening price** is a markup on the walk-away price, and patience is a number of rounds.
    - **Each offer below the table price** changes the mood, more for a lowball, with a reason line (DR10). The seller then concedes part of the gap toward the higher of the offer and the line, never past the line, rounded to a bazaar step (DR8).
    - **When patience runs out,** they accept if the offer meets the line, and walk if not.
  - **B. The words** live in one English `Strings` table keyed by line ID, never in a screen, until Phase 8 moves them into `strings.csv`.
  - **C. Font and icon files** are committed under `content/fonts/` with their licences, and the baker checks their SHA-256 pins. The baked atlases are **generated at build time** into `assets/` (kept out of git), so they can't drift from their sources.
  - **D. Icons:** Material Symbols Outlined at DESIGN §2.5's settings, as a subset with only the slice's icons (pause, add, remove, the coin), from Google Fonts. No other icons are downloaded.
  - **E. Out of the slice:**
    - "Last price?" and the call-back (Phase 9)
    - reputation changes (later phases), so the deal result shows the cost and the verdict
    - the godown (Phase 18), so a bought lamp isn't kept yet
    - after a deal, Continue brings the next walk-in seller, so play can go on
  - **F. Starting cash** is a placeholder of ₹2,500, as in the waitlist demo.
- **Edge cases handled in this phase:** SV-1, SV-2, SV-4, LC-1, LC-2, LC-3, IN-1, IN-3, IN-4, HE-1, DV-1, DV-3, DV-5, DC-1, HO-2, HO-3, BR-1 and BR-5. Each is placed in the plan's checkpoints above.

## Phase 7 — The legal pages and the first Play upload

STATUS: NOT STARTED

GOAL: The privacy policy and short terms are live at public URLs and linked inside the game, and the vertical slice is installed on the owner's phone from Play's internal testing track.

BUILD:
- **Privacy policy v1**, written only from PRD §8.5 and CR15: `site/bazaar-boss/privacy/index.html` and `privacy/v1/`.
- **Short terms v1:** game money has no value; no prizes; no warranty; Indian law; the contact. RESEARCH L-14 requires terms only with purchases; these are written now so testers have them.
- The owner publishes both through the same GitHub Pages route as the waitlist.
- **A minimal Settings screen, reached from Pause:**
  - Privacy policy, opened in the browser; with no browser, the URL is shown instead
  - Terms
  - the app version and the save version (F16 AC2, AC4)
- **The release build:** a signing config that reads the key from a file outside git; R8 on; `bundleRelease`.
- **Play Console**, the owner following the agent's checklist:
  - the privacy URL
  - Data safety, "No data collected or shared", and the ad ID set to "No"
  - the content-rating questionnaire, after reading its gambling definitions (Q16)
  - target audience: 18+
  - the internal testing track, with the owner as tester; upload the bundle
- The refund terms wait for purchases (Phase 38): v1 takes no money.

OUT OF SCOPE: The store listing's text and images (Phase 30); the closed track; the rest of Settings (Phase 22).

EDGE CASES: SV-1 (the release build reads its own saves), ST-2 (forms that match the build), ST-4 (16 KB, checked at upload), OP-5 (the policy URL is permanent).

TESTS: CODE: the Settings view shows both versions. INTEGRATION: `aapt2 dump permissions` on the release APK. USER: Settings' states; the browser hand-off, and its no-browser fallback.

READS: PRD §8.1, §8.2 (CR13–CR18), §8.5; RESEARCH L-14; TRD §1.3 (Ship), §5.3, §5.4, §6.1; APP-FLOW §1.7; DESIGN §7.

DONE WHEN:
1. CODE — `./gradlew check :android:assembleRelease :android:bundleRelease` succeeds → the output.
2. INTEGRATION — `aapt2 dump permissions` on the release APK lists nothing beyond AndroidX's app-private permission → the output.
3. INTEGRATION — the release (R8) build plays the slice, is killed, and resumes → screenshots before and after.
4. USER — Settings (from Pause) shows Privacy policy, Terms and both versions. Privacy policy opens the live page; with no browser, the URL shows → screenshots.
5. PRODUCT — the policy and the terms are live at `…/bazaar-boss/privacy/`, `…/privacy/v1/` and `…/terms/` → the URLs and 360-px screenshots.
6. PRODUCT — Play Console has the privacy URL, Data safety "No data collected or shared", the ad ID set to "No", the content rating, and target audience 18+ → a screenshot of each.
7. PRODUCT — the internal-track build is installed on the owner's phone from Play → a screenshot from the phone.

ASK FIRST IF: Play Console asks for anything paid, or for identity steps not yet done; the rating questionnaire's answers aren't clear (Q16); Play flags 16 KB pages or the target API.

DEPENDS ON: Phase 6; Phase 2 (the upload key, the Play Console entry, the game's email).

PLAN MODE: Yes. It makes statements about user data, and it handles the signing key.

PROGRESS:

## Phase 8 — The words: the content pipeline and three voices

STATUS: NOT STARTED

GOAL: Every line the game shows comes from `content/strings.csv`, checked by the validator. Switching the voice in the dev menu turns the haggle's next line into Tanglish or Hinglish.

BUILD:
- **The content sources:** `content/strings.csv` (`id | en | tanglish | hinglish | max_len | note`), `items.json`, `customers.json`, `market.json`, `tuning.json`, `banned/*.txt`.
- **`tools:validateContent`**, which fails on:
  - IDs: malformed, duplicated, or failing to resolve
  - placeholders that differ between voices
  - `max_len`, checked against the longest possible fill (CL-2)
  - banned words, matched as whole words, with a reviewed allowlist (CL-4)
  - a line about a customer without both gender variants (CL-1)
  - encoding: UTF-8 only; a byte-order mark stripped; invisible characters named by code point, line and voice (IN-2)
  - any character missing from the baked font
- **`tools:buildContent`**, writing `assets/content/pack.json` and `assets/strings/{en,tanglish,hinglish}.json`.
- **`persist.ContentPackReader`**: one parser, used by both the game and the validator.
- **`core.text.Strings`**: the chosen voice plus English; in debug builds, English fills a missing line.
- **The text test screen** in `devtools`, listing every line in all three voices.
- **Voice switching** in the dev menu.
- The slice's lines move into the CSV, with sample Tanglish and Hinglish for the haggle.

OUT OF SCOPE: Writing the full dialogue (Phases 26–27); the voice picker (Phase 22); the `ids-ever-shipped` baseline (Phase 30).

EDGE CASES: IN-2, CL-1, CL-2, CL-4, DV-1 (the font check covers formatted numbers), BR-4 (the guard is built now; its baseline comes at release).

TESTS: CODE: a failing fixture and a passing fixture for each validator rule. INTEGRATION: CSV → pack → loaded by the game; a release build fails on a missing line. USER: the text test screen in 3 voices, at M and XL.

READS: TRD §4.4, §4.11, §12; PRD F13, DR24, CR2, CR4; DESIGN §7; LLD §5 (validation), §6 row 11; EDGE-CASES IN-2, CL-1, CL-2, CL-4.

DONE WHEN:
1. CODE — `./gradlew check` passes; each validator rule has a fixture that fails and one that passes → the output, listing them.
2. INTEGRATION — editing a line in `strings.csv` and rebuilding changes it on the emulator. A release build with a missing Hinglish line fails to build → the build output and a screenshot.
3. USER — the text test screen shows the haggle's lines in English, Tanglish and Hinglish, at M and at XL on 316 dp → screenshots; all 16 checklist answers yes.
4. PRODUCT — F13 AC1: the validator stops a build with a missing line, a broken placeholder, an over-length line or a banned word → the test output.

ASK FIRST IF: The CSV needs columns beyond TRD §4.4's (for example, the gender variants: a new column or paired IDs; the owner chooses).

DEPENDS ON: Phase 6.

PLAN MODE: No.

PROGRESS:

## Phase 9 — The full haggle: five personalities, both directions

STATUS: NOT STARTED

GOAL: Haggle with all five personalities, buying from sellers and selling to customers:
- "Last price?", walking away and the call-back
- a lesson after a walk-away, and a verdict after a deal
- customers one after another

The simulator shows that no habit wins.

BUILD:
- **`haggle.Personalities`**: five, each punishing one habit (F3).
- **`HaggleRules`**:
  - DR8, DR10, DR12
  - DR13: one "Last price?", always on the customer's side of the line (HE-10)
  - DR14: one call-back
  - lessons (F2 AC8)
- **`QuickButtons`** in both directions: +10% and +25% when selling (HE-1).
- **`BadFaith`** says when bad faith happened (DR22). **`player.Reputation`** applies the change from the next customer (DR23).
- **A customer chain:** after each result, the next customer comes, with cash and stock carried over. It's a minimal queue in `game`; the real stall is Phase 14.
- **Personality lines** in English, through the pipeline.
- **The `sim` module:** 7 bots (lowballer, creeper, splitter, impatient, honest, walker, last-price-first; HE-3), and a `BotTournament` report.

OUT OF SCOPE: Faces (Phase 11); Inspect (Phase 15); the stall (Phase 14); final tuning (Phase 29); the full dialogue (Phase 26).

EDGE CASES: HE-1 (selling), HE-3, HE-10, DC-1 (quick buttons at small prices), LC-3 (the walk-away Lesson is an owed result).

TESTS:
- **CODE:**
  - DR6–DR14 and F2's criteria, as unit tests
  - properties over 1,000 seeds × 5 personalities: HE-10, and DR2 (cash never below ₹0)
  - a golden replay
- **INTEGRATION:** the replay through `Game` and real files.
- **USER:** the haggle with each personality, including the call-back, "Last price?" and a lesson.

READS: PRD F2, F3, F8, DR6–DR14, DR22, DR23; TRD §4.3, §5.2; LLD §2.4, §7.1; EDGE-CASES HE-1, HE-3, HE-10.

DONE WHEN:
1. CODE — `./gradlew check` passes → the output, listing the DR6–DR14 tests and the property tests.
2. CODE — `./gradlew sim:run`: each of the 7 bots loses against at least one personality → the report, committed.
3. USER — a call-back; "Last price?" used, with its chip greyed out; a lesson after a walk-away; selling with +10% and +25% → screenshots; all 16 answers yes.
4. PRODUCT — F2 AC6–AC10 (both directions); F3 AC1 (the simulator); F8 AC1, AC2, AC5 → each checked on the emulator or in the simulator, noted in PROGRESS.

ASK FIRST IF: Q8 (bohni) is still open: decide it before code (recommended: one engine rule plus a few lines). Also if the tournament shows a habit that wins and tuning can't fix within the phase: that's a design change to a DR.

DEPENDS ON: Phases 6 and 8.

PLAN MODE: No.

PROGRESS:

## Phase 10 — Spike: faces on a cheap phone

STATUS: NOT STARTED

GOAL: Know what drawing faces into textures costs in memory and time on a 2–3 GB phone, and whether screen tests need libGDX's headless backend (LLD U12, U18).

BUILD (on branch `spike/faces`, thrown away):
- Draw 8 shape-built faces into textures, on LowEnd_A9 and on the borrowed phone if it's in hand; measure milliseconds and megabytes.
- Force a context loss, and watch what happens.
- Decide U18 from how much logic the screens hold.
- Write the answers into LLD §10, and the performance rules into TRD §4.10.

OUT OF SCOPE: The real face kit (Phase 11).

EDGE CASES: LC-5 (the GL context), DV-4.

TESTS: None kept.

READS: IDEA D2; DESIGN §2.7 (#5, Face); TRD §0.4, §4.10; LLD §10 U12, U18.

DONE WHEN:
1. LLD §10's U12 and U18 rows carry dated answers with their evidence: milliseconds per face and megabytes for 8 faces on LowEnd_A9 (and on the borrowed phone, if available); what happens on context loss; a screenshot of the 8 faces.

ASK FIRST IF: The answer to U18 is "yes": the headless backend is a download.

DEPENDS ON: Phase 6.

PLAN MODE: No.

PROGRESS:

## Phase 11 — Faces and the haggle lab: the G1 build

STATUS: NOT STARTED

GOAL: Customers have drawn faces that show their mood beside the mood word, a stranger can play mixed haggles for more than 10 minutes, and debug builds have the haggle lab.

BUILD:
- **`core.art.FaceKit`** (IDEA D2):
  - skin and hair colours from the palette's scene colours
  - an expression for each mood, with a 150 ms crossfade (100 ms with animations off, DV-8)
  - redrawn after a context loss (LC-5)
- **The haggle screen at XL on 316 dp:** the history collapses to the last two rounds plus "Earlier offers" (DV-2).
- **The haggle lab** in `devtools`:
  - choose a personality, an item and a seed; play N rounds with one habit
  - debug builds only: the hidden walk-away price shows here
- **The replay hash**, printed on the phone from the dev menu.
- **The G1 build:** a mixed chain of buying and selling, with cash carried over, uploaded to the internal track.

OUT OF SCOPE: The stall (Phase 14); the rest of the art (the stall scene, items); sound.

EDGE CASES: LC-5, DV-2, DV-8.

TESTS: CODE: the view builders. INTEGRATION: the replay's hash on the phone equals the JVM's. USER: faces in 3 moods for each personality; XL on 316 dp; the lab.

READS: DESIGN §2.7 (Face, Speech line, Meter), §4 (motion), §6, §10; PRD F2 AC2, F3 AC3, F20 AC3; LLD §6 row 9, §7.3.

DONE WHEN:
1. CODE — `./gradlew check` passes → the output.
2. INTEGRATION — the golden replay's hash, printed on the phone from the dev menu, equals the JVM test's → both outputs.
3. USER — each personality's face in 3 moods, at M and at XL on 316 dp, in both navigation modes. The offer history never pushes Offer off screen → screenshots; all 16 answers yes.
4. USER — 10 minutes of mixed haggles on the emulator with no dead end → a screenshot about every 2 minutes.
5. PRODUCT — F2 AC2 (face, mood word and patience shown; mood never by colour alone); F3 AC3 (women and men over a week of customers, checked in the lab); F20 AC3 (frame time p95 on LowEnd_A9, from the dev overlay) → each noted.
6. PRODUCT — the G1 build is on the internal track and installed on the owner's phone → a screenshot from the phone.

ASK FIRST IF: Faces miss the frame budget on LowEnd_A9 after Phase 10's rules are applied.

DEPENDS ON: Phases 9 and 10; Phase 7 (the internal track).

PLAN MODE: No.

PROGRESS:

## Phase 12 — G1: is the haggle fun?

STATUS: NOT STARTED

GOAL: Know whether to go on. G1 passes when at least 3 of 5 people keep playing past 10 minutes without being asked, or ask to keep playing (PRD §12.3; LLD U10).

BUILD:
- **Owner:** hand the phone with the G1 build to each of the 5 playtesters, one at a time. Use a stopwatch, and don't coach. Note the minutes played, whether they asked to continue, and one quote each.
- **Agent:** a G1 sheet to fill in; afterwards, the result goes into PRD §12.3 and LLD U10.
- **If it fails:** rework the haggle (Phases 9 and 11, at most 2 rounds), then G1 again. After two failed reworks, stop.

OUT OF SCOPE: Changing the build during the playtest.

EDGE CASES: None: this is a measurement.

TESTS: None.

READS: PRD §12.3 (G1), §3; IDEA → Business verdict §8.

DONE WHEN:
1. PRODUCT — five rows (minutes, asked to continue, a quote) and the decision (go, rework, or stop) are in PRD §12.3 and LLD U10 → the doc change.

ASK FIRST IF: Fewer than 5 people can play within two weeks of Phase 11.

DEPENDS ON: Phase 11; Phase 2 (the playtesters).

PLAN MODE: No.

PROGRESS:

If Phase 1 landed in the middle band (370–1,599), building stops here, and the Business verdict is re-run with G1's result.

---

# M2 — Every v1 feature

## Phase 13 — Saves that survive anything

STATUS: NOT STARTED

GOAL: A damaged, unreadable, newer or half-written save never costs the player more than it must, and every case shows its card. A full phone shows a lasting banner while play goes on.

BUILD:
- **Backups:** `bak1` and `bak2`, rotated at day end, and at load only when the file changed (SV-8).
- **`Invariants` and `Repairs`:** repair before reject (SV-3).
- **A newer save is blocked**, judged by `formatVersion`, `writtenBy` or content this build doesn't know (SV-7). Its card offers Update on Play and Write to us.
- **The loading marker**, so a crash during load starts from `bak1` next time (SV-6).
- **Storage full:** a lasting banner, "Couldn't save. Phone storage is full. Free up some space and the game will save again." (SV-5)
- **Launch routing follows the save file** (SV-9).
- **The launch cards:** Restored your backup; Couldn't read any save, with Report prefilled with "lost progress"; Newer save, blocking play; Welcome back from an Android restore.
- The desktop build replaces the save file through `PlatformServices` (SV-4).

OUT OF SCOPE: Auto Backup's rules (Phase 25); Start over (Phase 25); migrations beyond the v1 fixture's scaffolding.

EDGE CASES: SV-3, SV-4 (desktop), SV-5, SV-6, SV-7, SV-8, SV-9, CL-3 (the card's date is fixed English).

TESTS:
- **CODE:** a fixture for every boundary state loads (SV-3); the v1 fixture loads and its values compare.
- **INTEGRATION:**
  - a damaged `game.json`; all three files damaged; a newer `writtenBy`; the marker present; ENOSPC
  - each through real files, with `FaultySaveFiles`
- **USER:** the four launch cards and the banner, through fixtures.

READS: TRD §4.5, §4.10, §6; PRD F15, §6 (overlays); APP-FLOW §1.3; LLD §4; EDGE-CASES SV-3 to SV-9, §7 (storage full).

DONE WHEN:
1. CODE — `./gradlew check` passes → the output, listing the repair fixtures.
2. INTEGRATION — damaged `game.json` → the backup loads, with its card. All three damaged → the files move to `broken/`, and a new game starts, with its card. Newer `writtenBy` → the block card, the files untouched, the same hash before and after. The marker → loads from `bak1` → the test report.
3. INTEGRATION — on the emulator, fill storage with `fallocate` (EDGE-CASES §7) and play: the banner shows and the last good save stays intact. Free the space: the next commit clears the banner → screenshots, and `df` before and after.
4. USER — the four launch cards and the banner, at M and at XL on 316 dp → screenshots; all 16 answers yes.
5. PRODUCT — F15 AC2, AC3, AC4 and AC7 → each checked, noted in PROGRESS.

ASK FIRST IF: The save needs fields beyond TRD §4.5 and LLD L-5.

DEPENDS ON: Phase 12 (G1 passed); Phase 6.

PLAN MODE: Yes: the player's saved data.

PROGRESS:

## Phase 14 — The stall and the market day

STATUS: NOT STARTED

GOAL: The stall is home. A day brings 5–8 customers (sellers and buyers asking for what's on display), you choose whom to serve, the day ends to "Day N done", and "Start Day N+1" begins the next.

BUILD:
- **`day.DayState`** with its phases: morning, trading, evening.
- **`DayPlanner`:** 5–8 visits; buyers want displayed items; walk-in sellers bring items.
- **`stock.Display`:** slots, auto-display (F6 AC8), and taking items off display.
- **The stall screen:**
  - cash in Indian grouping, with the coin icon; reputation; the day number; display slots ("Empty"); waiting customers; mute
  - its three states; the morning has no auction yet
- **The evening:** "Day N done", with "Start Day N+1". A placeholder for the kanakku until Phase 19.
- Back at the stall asks "Leave the game?" (F15 AC6).
- **The customer whose item has gone** asks for another, or leaves quietly (HE-7).
- The scene: the awning, the counter and the price tag (DESIGN §0).

OUT OF SCOPE: The auction (Phase 17); the kanakku, goal bar and tier-up (Phase 19); low cash and Today's special (Phase 20).

EDGE CASES: HE-7, IN-1 (serving), LC-3 (owed results as the day changes).

TESTS: CODE: `DayPlanner`, auto-display, HE-7. INTEGRATION: a whole day replayed through `Game` and real files. USER: the stall's three states; journey 2 without the auction.

READS: PRD F6, §3, §6 (stall); APP-FLOW §1.2, §1.8 row 2, §2.2; DESIGN §0, §2.7, §5; EDGE-CASES HE-7.

DONE WHEN:
1. CODE — `./gradlew check` passes → the output.
2. INTEGRATION — a replayed day leaves exactly its deals in the save, read through `run-as` → the test report.
3. USER — the stall in morning, trading and evening, with empty slots and the "Leave the game?" card → screenshots; all 16 answers yes.
4. USER — journey 2 without the auction: start the day, serve the customers, the day ends, start the next. Each sale takes 3 taps → a screenshot per step, and the tap counts.
5. PRODUCT — F6 AC1, AC2, AC3, AC5, AC7, AC8; F15 AC6 (at the stall) → each checked.

ASK FIRST IF: The day loop needs a rule PRD §7 doesn't have.

DEPENDS ON: Phases 12 and 9.

PLAN MODE: No.

PROGRESS:

## Phase 15 — Items, clues and Inspect

STATUS: NOT STARTED

GOAL: Forty items in about six categories, each with its condition, rarity, fake chance and 2–3 clues. Inspect shows the clues in words. A fake always has a clue that gives it away, and "Found X of 40" counts each new kind once.

BUILD:
- **Content:** 40 items, validated: every item has all six properties and 2–3 clues; every fake has a giveaway clue (DR21).
- **`stock.ItemFactory`:** fake or genuine, fixed at creation (DR20).
- **`stock.Clues`** and Inspect, with Q4's recommended cost: each tap mid-haggle reveals one clue and costs a point of patience; in the godown, inspecting is free.
- **The Inspect screen,** reached from the haggle, with "Look closer" for clues not yet found.
- **When the last point of patience goes** while Inspect is open, Inspect closes and the walk-away shows (HE-12).
- **The found counter** (F4 AC4).
- **Items drawn as shapes** (`ItemKit`).

OUT OF SCOPE: Inspect from the godown (Phase 18); Q5's unknowing sale, if it's still open.

EDGE CASES: HE-12, CL-2 (item names in the worst-case fill).

TESTS: CODE: DR20, DR21, Q4's cost, HE-12; the validator over 40 items. USER: Inspect's states.

READS: PRD F4, DR20–DR21, Q4, Q5; TRD §4.4 (ItemDef, ClueDef); DESIGN §2.7; EDGE-CASES HE-12.

DONE WHEN:
1. CODE — `./gradlew check` passes, with the validator passing over all 40 items → the output.
2. USER — Inspect with clues found; "Look closer"; a fake given away; the customer leaving during Inspect → screenshots; all 16 answers yes.
3. PRODUCT — F4 AC1–AC4 → each checked.

ASK FIRST IF: Q4 (the cost of inspecting) or Q5 (selling a fake unknowingly) is still open. Their recommended answers are in PRD §10; the owner decides before code.

DEPENDS ON: Phases 12, 8 and 9.

PLAN MODE: No.

PROGRESS:

## Phase 16 — Spike: the auction on a cheap phone

STATUS: NOT STARTED

GOAL: Know whether the auction holds 30 fps on LowEnd_A9 with two rival faces, the countdown, and the tick clamped at 100 ms (LLD U11).

BUILD (on branch `spike/auction`, thrown away):
- A synthetic auction scene with 2 faces and a live countdown.
- Force a 2 s frame stall and watch the countdown.
- Measure frame time p95 on LowEnd_A9, and on the borrowed phone.

OUT OF SCOPE: The real auction (Phase 17).

EDGE CASES: HE-5.

TESTS: None kept.

READS: PRD F5, DR16–DR17; TRD §0.4; LLD §6 row 3, §10 U11; EDGE-CASES HE-5.

DONE WHEN:
1. LLD §10's U11 row carries a dated answer with its evidence: frame time p95 on LowEnd_A9 (and on the borrowed phone), and how the countdown behaved through the forced stall.

ASK FIRST IF: 30 fps isn't reachable. The auction's design then changes.

DEPENDS ON: Phase 12.

PLAN MODE: No.

PROGRESS:

## Phase 17 — The auction

STATUS: NOT STARTED

GOAL: Each morning: a 10-second peek, then live bidding against two rivals. The hammer, and the result with every rival's estimate. Leaving costs nothing. A won lot goes into stock.

BUILD:
- **`auction.LotFactory`:** the lot is fixed before the peek (DR15).
- **`AuctionRules`:**
  - rivals bid from their estimate, budget, risk and favourite categories, and see only the peek (DR16)
  - bids are final; a new bid must beat the last; never while leading (HE-4); never with a full godown (DR17, HE-8)
  - leaving (DR18); estimates shown afterwards (DR19)
  - the tick advances at most 100 ms (HE-5); a tap in the hammer's frame counts; a resumed auction restarts its countdown in full (HE-6)
- **The auction screen:** peek, bidding, result; "Not enough cash"; Skip.
- **Pause** hides the peek (HE-11), and losing focus pauses the auction (LC-6).
- **The owed auction result** (LC-3).
- The stall's morning card leads here. **A won lot goes straight into stock,** with a one-line card, until Phase 18 builds the Reveal and Sort.

OUT OF SCOPE: The Reveal and Sort (Phase 18); Today's special lot (Phase 20); the rivals' faces beyond the face kit.

EDGE CASES: HE-4, HE-5, HE-6, HE-8 (bidding), HE-11, LC-3, LC-6.

TESTS: CODE: DR15–DR19, HE-4 to HE-6. INTEGRATION: a kill in the last second resumes paused, with the bids kept. USER: every state, and the notification shade during bidding.

READS: PRD F5, DR15–DR19, §7.7; APP-FLOW §1.7, §1.8 row 5, §2.2; TRD §5.2 (the auction commands); LLD §6 row 3; EDGE-CASES HE-4 to HE-6, HE-11, LC-6.

DONE WHEN:
1. CODE — `./gradlew check` passes → the output, listing the DR15–DR19 tests.
2. INTEGRATION — `am kill` in the countdown's last second, then relaunch: the auction comes back paused, with its bids and a full countdown → screenshots before and after.
3. USER — peek; bidding while leading ("You lead"), behind, and with too little cash ("Not enough cash"); the result with estimates; "Leave the auction?" → screenshots; all 16 answers yes.
4. USER — pull the notification shade down for 5 s during bidding: the countdown doesn't move → two screenshots, 5 s apart.
5. PRODUCT — F5 AC1, AC2, AC3, AC4, AC5, AC7, AC8 → each checked.

ASK FIRST IF: Rivals need information beyond the peek to make bidding interesting. That would break DR16.

DEPENDS ON: Phases 14, 15 and 16.

PLAN MODE: No.

PROGRESS:

## Phase 18 — The Reveal, Sort and the godown

STATUS: NOT STARTED

GOAL: A won lot empties from the gunny bag, item by item (the game's one motion moment), then Sort keeps or scraps each item behind one confirmation. The godown lists everything, with a filter, sorting and two kinds of empty state.

BUILD:
- **The Reveal:**
  - each item takes 280 ms, then a 120 ms gap; a tap shows the next at once; back shows them all
  - with animations off, items simply appear
  - it resumes after a kill (F5)
- **Sort:**
  - every item starts as Keep; marking scrap and confirming shows one card, "Scrap 3 items for ₹120?"
  - back keeps everything
  - taps are ignored for the first 400 ms (IN-5)
- **The godown screen:**
  - filter by category; sort by price paid, condition or newest, with `id` as the tiebreak
  - "Found X of 40"; Inspect, display and scrap from each row
  - its empty states: never had any, and filtered to nothing; empty wins over filtered (EF-2)
- **The soft limit of 60:** at or over it, bids and purchases say "Godown full" (HE-8).
- **Scrap prices.** The simulator's buy-to-scrap bot must lose money, and scrap never fills the goal bar (HE-2).

OUT OF SCOPE: Godown auctions (later, PRD §5); a recycled list (needed only if the limit ever goes).

EDGE CASES: IN-5, EF-2, HE-8, HE-2, DV-8 (the Reveal with animations off).

TESTS: CODE: Sort's decisions; the soft limit; the scrap price. The simulator: buy-to-scrap loses. INTEGRATION: a kill mid-reveal resumes, with the items already out in stock. USER: every state.

READS: PRD F5 AC6, AC9, AC10, F9, §7.6, §7.9; DESIGN §4 (the reveal); APP-FLOW §1.7, §1.8 rows 6a, 6b, 7; LLD §6 row 6; EDGE-CASES IN-5, EF-2, HE-2, HE-8.

DONE WHEN:
1. CODE — `./gradlew check` passes, and the simulator's buy-to-scrap bot loses money → the output and the report.
2. INTEGRATION — `am kill` mid-reveal, then relaunch: the items already out are in stock, and the rest come out → screenshots.
3. USER — the Reveal (normal, and with `animator_duration_scale 0`); Sort with the scrap card; the godown with items, filtered to nothing, never had any, and empty with a filter on → screenshots; all 16 answers yes.
4. PRODUCT — F5 AC6, AC9, AC10; F9 AC1–AC4 → each checked.

ASK FIRST IF: The soft limit needs a rule PRD §7.6 doesn't give.

DEPENDS ON: Phases 17 and 15.

PLAN MODE: No.

PROGRESS:

## Phase 19 — The kanakku, the goal and the tier-up

STATUS: NOT STARTED

GOAL: The day ends on the kanakku, a single screen: best deal first, tomorrow's teaser last. The goal bar fills only from haggled profit, starting at 20% (Periyappa's gift). Upgrading turns the stall into a shop, free, once.

BUILD:
- **`day.Kanakku`:**
  - the order: the best deal, the day's profit, reputation, today's deals, the best deal ever, the teaser (DR29)
  - "No deals today"; "Your first deal will show here."; a loss shown with a Lesson
- **`player.Goal`:**
  - profit since the last tier, only rising, only from haggled sales; a 20% head start; capped at 100% (DC-2)
- **The goal bar on the stall:** "₹18,400 of ₹25,000".
- **Upgrade on the kanakku** opens the tier-up card, once per tier, owed across a restart (LC-3).
- After the shop: the showroom reads "Coming in an update", and the next goal is "Find all 40 items" (Q7's recommendation).
- **The teaser** comes true the next day (DR27).

OUT OF SCOPE: The shop link on the tier-up card (Phase 37); the showroom's content (after G3).

EDGE CASES: DC-2, LC-3 (the tier-up), HE-2 (scrap never fills the bar).

TESTS: CODE: DR27, DR29; F7 AC1–AC4; DC-2. INTEGRATION: a kill during the tier-up card shows it again, once. USER: every state.

READS: PRD F7, F10, DR27, DR29, Q6, Q7; APP-FLOW §1.8 row 8, §2.2; DESIGN §4 (the tier-up is plain), §7; EDGE-CASES DC-2.

DONE WHEN:
1. CODE — `./gradlew check` passes → the output.
2. INTEGRATION — `am kill` while the tier-up card shows, then relaunch: it shows again, and not a third time → screenshots.
3. USER — the kanakku with deals, with none, with a loss and its Lesson, and with the first deal ever; the goal bar at 20%; the tier-up card; the shop's next goal → screenshots; all 16 answers yes.
4. PRODUCT — F7 AC1–AC4; F10 AC1–AC5 → each checked.

ASK FIRST IF: Q7 (what comes after the shop) is still open. Its recommendation is in PRD §10.

DEPENDS ON: Phase 14.

PLAN MODE: No.

PROGRESS:

## Phase 20 — Low cash, Today's special, teasers, welcome back and regulars

STATUS: NOT STARTED

GOAL: A player who goes broke always has a way out; nothing punishes being away; each calendar day brings one Today's special; regulars remember your last deal.

BUILD:
- **`player.Floor`:** what counts as broke (Q9's placeholder); the low-cash card with its three ways out; "Periyappa can help" on the stall while broke (F11).
- **`player.Loan`:** the terms shown first; no interest; one at a time; every sale repays at least ₹1 (HE-9).
- **A cheap-seller day.**
- **`day.Specials`** (DR26):
  - the date going back 1–2 days means "back tomorrow"; more than 2 days is treated as a correction
  - a special lot runs through the auction; leaving it asks "Leave? Today's special lot goes to the rivals." (HE-13)
- **`WelcomeBack`** after a gap of 3 or more calendar days (DR28). `NewGame` sets its dates (TC-2). The checks also run on resume (LC-7).
- **Regulars' memory** (F3 AC2).
- **The simulator:** no dead end, including broke with a loan open (F11 AC4).

OUT OF SCOPE: Notifications (after G3); restoration (after G3).

EDGE CASES: HE-9, HE-13, TC-2, TC-3, LC-7.

TESTS:
- **CODE:**
  - DR3 (with ₹4 sales), DR4 in the simulator, DR26 (back 1, 2 and 3 days, and forward), DR27, DR28
  - TC-2; F3 AC2
- **INTEGRATION:** clock changes through `FakeClock`.
- **USER:** every card; clock changes through `cmd alarm set-time`.

READS: PRD F6 AC4, AC6, F8, F11, F3, DR3, DR4, DR25–DR28, Q9; APP-FLOW §2.3; EDGE-CASES HE-9, HE-13, TC-2, TC-3, LC-7, §7 (the clock).

DONE WHEN:
1. CODE — `./gradlew check` passes; the simulator reports no dead end in its thousands of days → the output and the report.
2. INTEGRATION — on the emulator, set the clock 3 days ahead while the game is in the background, then bring it back: the welcome-back lot is offered. Set the date back a day: the special reads "back tomorrow" → screenshots, and the commands' output.
3. USER — the low-cash card; "Periyappa can help"; the loan's terms; a cheap-seller day; Today's special as a visitor and as a lot, with its leave wording; the teased customer; the welcome-back lot → screenshots; all 16 answers yes.
4. PRODUCT — F6 AC4, AC6; F11 AC1–AC5; F3 AC2; F8 AC3, AC4 → each checked.

ASK FIRST IF: Q9's "broke" threshold or the welcome-back gap needs a decision beyond its recommendation.

DEPENDS ON: Phases 19, 17 and 18.

PLAN MODE: No.

PROGRESS:

## Phase 21 — Sharing: the deal card and Share my stats

STATUS: NOT STARTED

GOAL: Share on a deal result opens Android's chooser with a card that says "Game money. No real money." Share my stats shows its exact text first, then opens the chooser.

BUILD:
- **The deal card:** a 1080 × 1080 PNG of the item, "bought ₹200 → sold ₹1,800", the coin icon, the name, and "Game money. No real money." No IDs, no banknotes (AB-1).
- **Saved to `cache/share/`** under a new name each time; files older than an hour are deleted at launch (HO-4).
- **The FileProvider** exposes `cache/share/` only, with read granted through `ClipData`.
- **`records.StatsText`**, exactly TRD §5.3's lines:
  - `Locale.ROOT` and ISO dates (CL-3); an impossible date reads "unknown" (TC-1)
  - play time counts only within 60 s of a tap (MS-4)
  - the date this game began, so a restart shows (MS-3)
- **The Share my stats preview** in Settings: two taps away, and zeros on a fresh install.
- **"Couldn't share"** when nothing takes the share.

OUT OF SCOPE: Rewards for sharing (never, F12); referral codes (never).

EDGE CASES: AB-1, HO-4, TC-1, CL-3, MS-3, MS-4.

TESTS: CODE: `StatsText`'s exact lines, under the five locales; the share text holds no IDs (`RecordingPlatform`). INTEGRATION: the FileProvider paths test; the cache clean-up. USER: the chooser, cancel, and the card itself.

READS: PRD F12, F18, CR1, §8.5; TRD §5.3, §4.7 (flag 1); DESIGN §8 (assets), §7; EDGE-CASES AB-1, HO-4, TC-1, MS-3, MS-4.

DONE WHEN:
1. CODE — `./gradlew check` passes → the output, listing the `StatsText` and no-ID tests.
2. INTEGRATION — two shares make two files in `cache/share/`, and the next launch deletes those older than an hour → `run-as ls -l cache/share` before and after.
3. USER — Share opens the chooser, and cancelling sends nothing; the card's PNG; the stats preview, with zeros on a fresh install → screenshots and the PNG; all 16 answers yes.
4. PRODUCT — F12 AC1–AC3; F18 AC1–AC3; IDEA H4-16 → each checked.

ASK FIRST IF: The card needs anything beyond F12's list: no IDs, no links.

DEPENDS ON: Phases 14 and 19.

PLAN MODE: Yes: data leaves the phone through the share sheet.

PROGRESS:

## Phase 22 — First launch and Settings

STATUS: NOT STARTED

GOAL: A fresh install opens on the voice picker, and one tap starts the first haggle, with the first offer inside 30 seconds. Settings has everything F16 lists.

BUILD:
- **The voice picker:** three voices, each with a sample line; mute. One tap goes straight into the first haggle with a patient seller, and the first hint says "All ₹ here is game money."
- **If two sellers leave without a deal,** the stall opens anyway (EF-1).
- **The first seller's price** always fits within the starting cash (a test).
- **Settings, in full:**
  - voice (the next line changes)
  - sound and mute; vibration
  - text size S, M, L or XL, which rebuilds every open screen (DV-7)
  - privacy policy and terms (from Phase 7)
  - credits and licences, with the OFL and Apache texts
  - the app and save versions
- **Hints:** one line each, the first time; seen only when dismissed.

OUT OF SCOPE: Report, Start over and the crash note (Phase 25); sound effects (Phase 23).

EDGE CASES: EF-1, SV-9 (the voice preselected when settings exist without a save), DV-7.

TESTS: CODE: launch routing; EF-1; the first seller's price. USER: `uninstall`, then reinstall: a stopwatch to the first offer; every Settings state.

READS: PRD F1, F13, F16, §6 (voice picker, settings); APP-FLOW §1.3, §2.1; DESIGN §2.2 (text-size steps), §6, §7, §8; EDGE-CASES EF-1, SV-9, DV-7.

DONE WHEN:
1. CODE — `./gradlew check` passes → the output.
2. USER — `"$ADB" uninstall $PKG`, reinstall, launch: the voice picker, then the first offer within 30 s and the first deal within 60 s → screenshots at 0 s, at the first offer and at the first deal, with times from `am start -W` and a stopwatch.
3. USER — Settings with each row, and each text size (M, then XL on 316 dp) → screenshots; all 16 answers yes.
4. PRODUCT — F1 AC1–AC5; F13 AC3; F16 AC1–AC4 → each checked.

ASK FIRST IF: The first offer can't be reached within 30 s on LowEnd_A9.

DEPENDS ON: Phases 14, 8 and 7.

PLAN MODE: No.

PROGRESS:

## Phase 23 — Sound, haptics, back and resume everywhere

STATUS: NOT STARTED

GOAL: Back does the safe thing on every screen, in both navigation modes. After Android kills the game, it comes back exactly where it was, on every screen. Sounds and haptics follow the mute and vibration settings.

BUILD:
- **`BackDispatcher`'s policy table**, row for row with APP-FLOW §1.7.
- **Resume on every screen;** owed results come first (LC-3).
- **Sound effects:** made with jsfxr by the owner, or CC0, as OGG, listed in Credits. There are no casino sounds (F14).
- **Mute** works on the voice picker, the stall and in Settings, takes effect at once, and survives a relaunch.
- **Haptics:** a 20–30 ms pulse only for a deal, an auction won and a tier-up, and only when vibration is on.
- **TalkBack** gets one spoken announcement (DV-9).
- **Split screen:** below the minimum size, a "Make the window bigger to play" card (DV-6).
- **Animations off:** fades only (DESIGN §4).

OUT OF SCOPE: Music (not in v1); notifications (after G3).

EDGE CASES: LC-3, DV-6, DV-9, DV-8.

TESTS: CODE: the policy table against APP-FLOW §1.7; the mute and vibration rules, with recording fakes. USER: back and process death on every screen, in both navigation modes.

READS: APP-FLOW §1.5, §1.7; PRD F14, F15 AC1, AC6, §7.7; DESIGN §4, §6; EDGE-CASES LC-3, DV-6, DV-9, §7.

DONE WHEN:
1. CODE — `./gradlew check` passes, with the back-policy test matching APP-FLOW §1.7 row for row → the output.
2. USER — back on every screen, in gesture and in 3-button navigation → a screenshot after each press.
3. USER — Home, then `am kill`, on every screen; each relaunch lands where it was, with owed results first → screenshots.
4. USER — TalkBack's announcement; the split-screen card → screenshots.
5. PRODUCT — F14 AC1–AC4; F15 AC1 and AC6, in both navigation modes → each checked.

ASK FIRST IF: Any CC0 pack needs a credit the Credits screen can't show.

DEPENDS ON: Phases 17 to 22: every screen exists; Phase 2's answer on the sounds (jsfxr or a CC0 pack).

PLAN MODE: No.

PROGRESS:

## Phase 24 — Spike: hand-offs and backups

STATUS: NOT STARTED

GOAL: Know whether the backup rules work as written, in the cloud and phone-to-phone, and how `restoreAnyVersion` behaves by default. Know which email apps keep the report's subject and body (LLD U13, U14).

BUILD (on branch `spike/handoffs`, thrown away):
- Draft rules in both files, with `<cloud-backup>` and `<device-transfer>`.
- Back up and restore with `bmgr` on two emulators, cloud and device-to-device.
- Restore a backup from a newer version.
- Send the same `ACTION_SEND` with a `mailto:` selector to Gmail and to one other email app.
- Write the answers into LLD §10 and TRD §4.11 and §6.3.

OUT OF SCOPE: The real rules and reports (Phase 25).

EDGE CASES: BR-2, HO-1.

TESTS: None kept.

READS: TRD §4.6, §4.7 (flag 2), §4.11, §6.3; EDGE-CASES BR-2, HO-1; Android's Auto Backup page (EDGE-CASES §8).

DONE WHEN:
1. LLD §10's U13 and U14 rows carry dated answers with their evidence: the `bmgr` output for each kind of restore, what each restore left in the app's files (`run-as ls`), and screenshots of the email app's compose screen in each app tested.

ASK FIRST IF: Testing a second email app means installing one. That's a download.

DEPENDS ON: Phase 12.

PLAN MODE: No.

PROGRESS:

## Phase 25 — Safety basics: reports, crash notes, Start over, backups, guards

STATUS: NOT STARTED

GOAL: The player can report a problem or a line by email, after reading every word of it. Crashes leave notes with no IDs. Start over wipes everything. Backups carry only what they should.

BUILD:
- **Report a problem:**
  - reasons listed only if they apply to this version
  - the whole message shown first, carrying the versions and no IDs
  - the crash note offered only when one exists
  - the email hand-off as Phase 24 found; with no email app, the address shown (F19)
- **Report this line:** a long-press on any line.
- **Crash notes:** at most 3, each at most 16 KB, no IDs; the crash is then passed on to Android's own handler.
- **Start over:**
  - asks twice
  - locks input, drains the writer, deletes everything, and raises a generation number (LC-4)
  - leaves AndroidX's `profileInstalled` behind
- **The backup rules** in both files and both sections (BR-2), with their test.
- **The merged-manifest guard** in full: permissions, exports, `allowBackup`, the back callback, `appCategory`, release builds not debuggable.
- **The restore card** for a save that came through Android's backup, checked on two emulators.

OUT OF SCOPE: "A bad ad" and "a purchase" as report reasons (they arrive with ads and purchases).

EDGE CASES: LC-4, BR-2, HO-1, SV-6 (the crash note), DC-3.

TESTS:
- **CODE:** the report body (reasons, versions, no IDs); the crash note; the backup-rule parse test; the guards.
- **INTEGRATION:**
  - Start over against a slow writer
  - a forced crash leaves a note, and Android's crash dialog still appears
- **USER:** every card; restore on a second emulator.

READS: PRD F15 AC5, F17, F19, CR13, CR16; TRD §4.6–§4.8, §4.11, §5.3; IDEA H4-11 to H4-13; EDGE-CASES LC-4, BR-2, HO-1.

DONE WHEN:
1. CODE — `./gradlew check` passes, listing the report, crash-note, backup-rule and manifest guards → the output.
2. INTEGRATION — Start over with the slow-commit switch on: the next launch is a first launch, and `"$ADB" shell "run-as $PKG ls -R files"` shows only `profileInstalled` → the output (IDEA H4-12).
3. INTEGRATION — a forced crash from the dev menu leaves a note, and the next Report a problem offers it → screenshots.
4. USER — Report a problem from reason to email app, plus its no-email-app fallback; Report this line; Start over's two confirmations → screenshots; all 16 answers yes.
5. PRODUCT — back up, then restore on a second emulator: the save and settings return, and the "Your progress from … is here" card shows → screenshots (F15 AC5, H4-13).
6. PRODUCT — F17 AC1–AC2; F19 AC1–AC5 → each checked.

ASK FIRST IF: The email hand-off can't carry the subject and body in Gmail.

DEPENDS ON: Phases 24, 22 and 13.

PLAN MODE: Yes: reports, crash notes, Start over and backups all touch the player's data.

PROGRESS:

## Phase 26 — Dialogue, part 1: the haggle in three voices

STATUS: NOT STARTED

GOAL: Every haggle line is written in English, Tanglish and Hinglish, with both genders, fits its box at XL, and passes the validator. The owner has read the Tanglish, and the Hinglish has gone to the native reader.

BUILD:
- **Lines** for the 5 personalities across every moment: opening, counters by mood, accept, walk, call-back, "Last price?", lessons, verdicts.
- **Each line** has gender variants and stays inside its length at the longest fill.
- **The owner** reads the Tanglish. **The Hinglish** goes to the reader (Q2): about 2 hours of their time.

OUT OF SCOPE: Lines for the other moments (Phase 27).

EDGE CASES: CL-1, CL-2, CL-4, IN-2.

TESTS: CODE: the validator over every haggle line. USER: the haggle in each voice at XL on 316 dp.

READS: PRD F13, DR24, CR4, Q2; DESIGN §7; IDEA §5.2 (writing short); TRD §4.4; EDGE-CASES CL-1, CL-2, CL-4.

DONE WHEN:
1. CODE — the validator passes over every haggle line, in all three voices → the output.
2. USER — the haggle in each voice, at XL on 316 dp, with nothing clipped → screenshots; all 16 answers yes.
3. PRODUCT — F3 AC4: no personality is tied to a region, religion, caste or community, by the owner's read; the Tanglish read is done; the Hinglish has been sent → the owner confirms.

ASK FIRST IF: A line needs a word the banned list blocks; the Hinglish reader asks for changes to the game's words (Q2).

DEPENDS ON: Phases 9 and 8; Phase 2 (the reader).

PLAN MODE: No.

PROGRESS:

## Phase 27 — Dialogue, part 2: everything else in three voices

STATUS: NOT STARTED

GOAL: Every line in the game exists in all three voices, and the Hinglish has had its native read (CR18, a release blocker).

BUILD: Lines for sellers, regulars, rivals and the auction; the kanakku and teasers; Today's special; welcome back; hints; every card a character speaks on. Menus stay in plain English (F13).

OUT OF SCOPE: Native-script menus (Q10: no).

EDGE CASES: CL-1, CL-2, CL-3.

TESTS: CODE: the validator over all content, plus a test that no release-build line ever falls back to English. USER: every screen with a character line, in each voice.

READS: PRD F13, CR18; DESIGN §7; EDGE-CASES CL-1, CL-2.

DONE WHEN:
1. CODE — the validator passes over all content, and the fallback test finds no English line standing in for a missing one → the output.
2. USER — every screen with a character line, in each voice, at XL → screenshots; all 16 answers yes.
3. PRODUCT — the Hinglish native read is done (CR18; F13 AC4) → the owner confirms, with no name in git.

ASK FIRST IF: The native read changes the game's words (Q2: kanakku, Periyappa).

DEPENDS ON: Phases 26 and 20.

PLAN MODE: No.

PROGRESS:

## Phase 28 — Spike: the oldest Android

STATUS: NOT STARTED

GOAL: Know whether the game installs and plays on Android 7.0 (API 24), the oldest version F20 promises (LLD U17).

BUILD (owner's yes to the download first):
- An API 24 emulator image.
- Install the current debug build and play the core loop.
- Record any crash, and give its fix a phase.
- Write the answer into LLD §10, and TRD TD-23.

OUT OF SCOPE: Fixes larger than the spike, which go into Phase 29 or a new phase.

EDGE CASES: SV-2 (APIs from Android 8 and later).

TESTS: None kept.

READS: PRD F20 AC6; TRD TD-23; LLD §10 U17; EDGE-CASES SV-2.

DONE WHEN:
1. LLD §10's U17 row carries a dated answer with its evidence: screenshots of the core loop on API 24, and every crash's stack trace with the phase that fixes it.

ASK FIRST IF: The image download isn't approved yet (Phase 2).

DEPENDS ON: Phase 12; Phase 2 (the download).

PLAN MODE: No.

PROGRESS:

## Phase 29 — Tuning and the cheap phone

STATUS: NOT STARTED

GOAL: Stall to shop takes 30–45 minutes in the simulator, with no dead end and no winning habit. The game meets its speed targets on a 2–3 GB phone, and Check 3's prediction is written down before any tester plays.

BUILD:
- **Q9's numbers:** starting cash, prices, patience, the broke threshold, the loan's size and share, display slots.
- **Tune with the simulator,** and record each number in TRD's tuning table.
- **Commit the simulator's dated report** before the closed test (MS-5).
- **Measure on LowEnd_A9 and the borrowed phone:** cold start p95, frame time p95 in the haggle, the auction and the Reveal, memory, and 10 minutes of battery (DV-4, DV-5).
- **Fix** whatever misses.

OUT OF SCOPE: New features; content beyond v1.

EDGE CASES: DV-4, DV-5, MS-5, HE-2, HE-3.

TESTS: CODE: the simulator's pacing, dead-end and tournament reports. USER: speed and battery on both phones.

READS: PRD §12.2 (Check 3), F7 AC5, F11 AC4, F20 AC3, Q9; TRD §0.4, the tuning table; LLD §6; EDGE-CASES DV-4, DV-5, MS-5.

DONE WHEN:
1. CODE — the simulator predicts a median of 30–45 minutes from stall to shop, finds no dead end, and every bot loses somewhere; the tuning table is filled → the committed report, and the TRD change.
2. USER — on LowEnd_A9 and the borrowed phone: cold start p95 ≤ 4 s; frame time p95 ≤ 33 ms in the haggle, the auction and the Reveal → `am start -W` times and the dev overlay's screenshots.
3. PRODUCT — Check 3's prediction is committed before the closed test (MS-5); F7 AC5's prediction is written down; F20 AC3 → the commit and the measurements.

ASK FIRST IF: The pacing target can't be reached without changing a DR; the phone misses its targets after fixes.

DEPENDS ON: Phases 20, 23 and 27; Phase 28; Phase 2 (the borrowed phone).

PLAN MODE: No.

PROGRESS:

---

# M3 — Ready for strangers

## Phase 30 — The release build and the store listing

STATUS: NOT STARTED

GOAL: A release build that passes every v1 acceptance criterion, with a listing that tells the truth, has gone to Play's closed track for review.

BUILD:
- **Release rules:**
  - the build refuses a `formatVersion` or `contentVersion` lower than the last release tag's (OP-1)
  - the `ids-ever-shipped.txt` baseline, which `bundleRelease` keeps up to date (BR-4)
  - tags: `v<versionName>-<versionCode>`
  - the format's fixture, `saves/v1.json`
- **The two-release save test:** release N saves a day; release N+1, one unrelated change later, must load it (SV-1).
- **The app icon,** drawn as shapes in code. It clears the `MissingApplicationIcon` warning.
- **The store listing:**
  - the title (≤ 30 characters), the short and full descriptions (CR7: no "no ads", "earn", "win cash", "approved", "registered" or "#1")
  - screenshots from the release build, taken with `adb`; the feature graphic
- **The release checklist** (IDEA H4-17): the merged manifest's permissions, Data safety and PRD §8.5 agree; the privacy policy is current.
- The closed-track release is submitted.

OUT OF SCOPE: Paid promotion; extra countries (CR10); a review prompt (later).

EDGE CASES: SV-1, BR-4, OP-1, ST-2, ST-4, ST-5.

TESTS: CODE: the version guard; `ids-ever-shipped`. INTEGRATION: the two-release save test; the release APK's permissions. USER: every screen and state on the **release** build.

READS: TRD §1.3 (Ship), §6, §10 (shipping safely); PRD CR7, CR9, CR13–CR18, F20; RESEARCH §3; DESIGN §8 (store assets), §10; IDEA H4-17; EDGE-CASES SV-1, BR-4, OP-1, ST-2, ST-4, ST-5.

DONE WHEN:
1. CODE — `./gradlew check :android:bundleRelease` passes, and lowering `formatVersion` makes the build fail → both outputs.
2. INTEGRATION — release N saves a day; release N+1 loads it on the same emulator → screenshots before and after.
3. INTEGRATION — `aapt2 dump permissions` on the release APK lists `VIBRATE` and AndroidX's own permission, nothing else → the output.
4. USER — every v1 screen and state, on the release build, at M and XL, in both navigation modes → `shots/p30-*`; all 16 answers yes.
5. PRODUCT — every v1 criterion, F1–F20, re-checked on the release build → a table in PROGRESS.
6. PRODUCT — the listing's draft and the release checklist → screenshots of both.
7. PRODUCT — the closed-track release is submitted → a screenshot of Play Console's status.

ASK FIRST IF: The rating questionnaire, Data safety or target audience don't fit (Q16); Play rejects anything; a check from earlier phases fails on the release build.

DEPENDS ON: Phases 25, 27 and 29; Phase 7.

PLAN MODE: Yes: the data forms and the signing key.

PROGRESS:

## Phase 31 — The closed test: 14 days, then G2

STATUS: NOT STARTED

GOAL: At least 12 testers stay opted in for 14 days in a row, the bugs they find are fixed with tests, and G2 is decided on Checks 1 and 2.

BUILD:
- **Owner:**
  - invite the testers and check the opted-in count daily (ST-1)
  - send exactly three messages (Q3): on day 0, welcome, how to report, and please don't review (AB-2) or start over (MS-3); on day 8, "please send Share my stats"; on day 14, thanks
  - log feedback by tester number
- **Agent:** fix each reported bug in its own release, each with a regression test; compute Checks 1–3 from the stats received, with MS-2's rule for missing stats.
- The Hinglish read is already done (Phase 27).

OUT OF SCOPE: New features during the test; asking anyone to play.

EDGE CASES: ST-1, MS-2, MS-3, AB-2.

TESTS: CODE: a regression test for every bug fixed.

READS: PRD §12.2, §12.3 (G2), CR17, Q3; EDGE-CASES ST-1, MS-2, MS-3, AB-2.

DONE WHEN:
1. PRODUCT — at least 12 testers opted in for 14 days in a row → Play Console's tester count on days 1, 7 and 14.
2. PRODUCT — stats came from at least 10 testers, or MS-2's rule was applied; Checks 1–3 computed → the numbers in PRD §12.2.
3. CODE — every bug found has a regression test, and its fix was released → the test names and the release notes.
4. PRODUCT — G2 is decided → PRD §12.3.

ASK FIRST IF: Opted-in testers drop below 12; G2 fails. Its rule: fix the top reason testers stopped, retest once, or stop.

DEPENDS ON: Phase 30; Phase 2 (the testers).

PLAN MODE: No: fixes are small, each with a test. Plan mode applies again if a fix touches the save or data.

PROGRESS:

## Phase 32 — Production access and the public release

STATUS: NOT STARTED

GOAL: The game is public on Google Play in India, rolled out in stages, with the inbox and the launch email handled.

BUILD:
- **Owner:**
  - apply for production access, answering with specifics (RESEARCH §3 #1)
  - after the review (up to about 7 days), roll out to 20%, then 50%, then 100%, watching Android vitals at each step
  - turn on Play Console's email alerts
- **Agent:**
  - stock replies for the top 5 report reasons (TRD §12)
  - the line "The only official version is on Google Play", on the waitlist page and in the policy (AB-4)
- **The launch email:** sent once, not from the support address, in batches of at most 450, and the list deleted within 30 days (OP-2, H4-18).
- A calendar note for Play's yearly target-API rule (ST-3).

OUT OF SCOPE: Purchases and ads (after G3).

EDGE CASES: OP-2, AB-4, ST-3, AB-6.

TESTS: None new: the release passed Phase 30.

READS: PRD §12.3, CR10, CR12; TRD §10, §12; RESEARCH §3; EDGE-CASES OP-2, AB-4.

DONE WHEN:
1. PRODUCT — production access is granted → a Play Console screenshot.
2. PRODUCT — each stage (20%, 50%, 100%) keeps crashes below 1.09% and ANRs below 0.47% → Android vitals screenshots at each stage.
3. PRODUCT — the launch email went out in batches from a non-support account, and the list's deletion date is set → the owner confirms.
4. PRODUCT — the stock replies exist, and the official-version line is live → the doc and the page.

ASK FIRST IF: Production access is refused; vitals cross Play's bad-behaviour thresholds at any stage. Halt the rollout and forward-fix (OP-1).

DEPENDS ON: Phase 31 (G2 passed).

PLAN MODE: No.

PROGRESS:

---

# After M3 — money, behind gates

## Phase 33 — G3: the first 30 days

STATUS: NOT STARTED

GOAL: Know whether free channels bring players: at least 1,000 installs in the first 30 days, and D7 retention of 10% or more (PRD §12.3). That decides whether purchases follow.

BUILD: Owner: read Play Console's installs and day-7 retention at day 30. Agent: record them, and the decision, in PRD §12.3.

OUT OF SCOPE: New content (a new plan after G3).

EDGE CASES: None.

TESTS: None.

READS: PRD §12.3, §12.4; MONETISATION (the decision).

DONE WHEN:
1. PRODUCT — 30-day installs, D7 retention and the decision are in PRD §12.3 → a Play Console screenshot and the PRD change.

ASK FIRST IF: — (the decision is the owner's).

DEPENDS ON: Phase 32.

PLAN MODE: No.

PROGRESS:

## Phase 34 — Advisers and the merchant account for the first sale

STATUS: NOT STARTED

GOAL: The CA's GST answer (ADV-2) and the lawyer's on seller status (ADV-3) are in hand, and Play Console's merchant account is verified, before any purchase code ships (Q19).

BUILD:
- **Owner:**
  - book both advisers (about ₹1,000–₹7,000 together [A], paid once; Q19 approved it)
  - set up and verify the payments profile; the home address on Play is accepted (H5-12)
  - register for GST if the CA says so, and choose to file returns yourself or pay a service (MONETISATION §3)
- **Agent:** record the answers in MONETISATION and the PRD (CR28).
- **It starts at the public release (Phase 32),** so the answers arrive while G3 runs.

OUT OF SCOPE: Any purchase code.

EDGE CASES: GT-2 (account setup notes).

TESTS: None.

READS: MONETISATION §3, §8; PRD CR28–CR32, Q15, Q19; RESEARCH L-8 to L-10.

DONE WHEN:
1. PRODUCT — ADV-2's and ADV-3's answers are recorded in MONETISATION and PRD CR28 → the doc change.
2. PRODUCT — the merchant account is verified → a Play Console screenshot.
3. PRODUCT — the GST position is settled: registered or not, and how returns are filed → the owner confirms.

ASK FIRST IF: G3 looks like failing by day 15, before paying the advisers; a monthly filing fee would be more than the likely purchase income (MONETISATION §3).

DEPENDS ON: Phase 32.

PLAN MODE: No: no code. It's money and legal, so the owner decides.

PROGRESS:

## Phase 35 — Spike: Play Billing 9

STATUS: NOT STARTED

GOAL: Know how Play Billing 9 behaves on API 24 and 36 with license testers: pending purchases, acknowledgement, restore, and re-locking after a refund (LLD U15).

BUILD (on branch `spike/billing`, thrown away): one test product in Play Console; license testers; the test cards that always approve, always decline, and approve slowly; a refund from order management. Answers go into LLD §10 and TRD §3.3.

OUT OF SCOPE: The real shop (Phases 36–38).

EDGE CASES: MN-2, MN-5.

TESTS: None kept.

READS: MONETISATION §7; TRD §3.3, §5.5; LLD §10 U15.

DONE WHEN:
1. LLD §10's U15 row carries a dated answer with its evidence: screenshots of each test card's flow on API 24 and API 36, and a refunded order's re-lock.

ASK FIRST IF: Play Billing 9 needs a change to the permission allowlist beyond `BILLING` (CR33).

DEPENDS ON: Phases 34 and 33 (G3 passed).

PLAN MODE: No: a spike.

PROGRESS:

## Phase 36 — Purchases 1: billing

STATUS: NOT STARTED

GOAL: A purchase is granted once only when it's paid, acknowledged, restored at every launch, and re-locked after a refund.

BUILD:
- `shop.Entitlements` and `shop.Catalogue`: products are cosmetic only, and granting touches nothing but the owned set (DR36).
- The `BillingPort` and its Play Billing 9 implementation.
- Pending purchases grant nothing until paid (DR38).
- A purchase delivered twice is granted once.
- `BILLING` joins the permission allowlist (CR33).
- PRD §8.5's purchases row and the Data safety plan are updated first (Q18).

OUT OF SCOPE: The shop screen (Phase 37); the pages (Phase 38).

EDGE CASES: MN-2, MN-5, MN-6, GT-2.

TESTS: CODE: `Entitlements`; `Catalogue` grants nothing but looks (H4-8). INTEGRATION: `FakeBilling` scripts: approve, decline, pending then approve, refund, delivered twice. USER: license testers, MONETISATION §7 checks 1–7.

READS: MONETISATION §7; PRD F21, DR36–DR38, CR29–CR33, §8.5; TRD §3.3, §5.5; IDEA H4-8, H4-10.

DONE WHEN:
1. CODE — `./gradlew check` passes → the output, listing the H4-8 test.
2. INTEGRATION — every `FakeBilling` script ends with the right owned set → the test report.
3. USER — MONETISATION §7 checks 1–7 run with license testers on a device → a screenshot of each outcome.
4. PRODUCT — F21 AC4–AC8 → each checked.

ASK FIRST IF: Anything here needs a server; Play's Data safety needs more than §8.5's purchases row (Q18).

DEPENDS ON: Phase 35.

PLAN MODE: Yes: payments.

PROGRESS:

## Phase 37 — Purchases 2: the shop

STATUS: NOT STARTED

GOAL: The shop sells the Dealer Pack and themes at Play's own prices, and a bought theme shows on the stall and on the deal card. The game suggests the shop exactly once, on the first Tier-up card.

BUILD:
- **The shop screen:** a skeleton while products load; the products, with Play's prices; owned items; "Payment pending"; "Can't reach Google Play".
- **Themes** on the stall and the deal card.
- **The "See the shop" link** on the first Tier-up card, once.
- **Buy** is locked until the purchase flow returns (MN-4).
- **The price-literal guard** (H4-9).
- Journey 4 (APP-FLOW §2.4).

OUT OF SCOPE: Paid randomness, discounts, timers or "best value" badges (never; CR5).

EDGE CASES: MN-4, GT-2, MN-6.

TESTS: CODE: the price-literal guard. USER: every shop state; journey 4.

READS: PRD F21; MONETISATION §4, §7; APP-FLOW §1.8 row 24, §2.4; DESIGN §2.7 (Skeleton), §5.

DONE WHEN:
1. CODE — `./gradlew check` passes, with the price-literal guard → the output.
2. USER — the shop loading, with products, with an item owned, with a payment pending, and unable to reach Play; the tier-up link; a theme on the stall and on the deal card → screenshots; all 16 answers yes.
3. USER — journey 4 takes 3 taps → screenshots.
4. PRODUCT — F21 AC1–AC3, AC9, AC10; MONETISATION §7 checks 8–11 → each checked.

ASK FIRST IF: Play's product setup conflicts with DR37 (prices only from Play).

DEPENDS ON: Phase 36.

PLAN MODE: Yes: payments.

PROGRESS:

## Phase 38 — Purchases 3: the refund, terms and grievance pages, and the release

STATUS: NOT STARTED

GOAL: The refund policy (Q20), terms covering purchases, the grievance page and the in-game Help entry are live, and the release with purchases has rolled out.

BUILD:
- **Pages:**
  - terms v2
  - the refund policy, with Q20's terms: Google within 48 hours; ours within 30 days for any reason; at any time if an item doesn't work; money back only through Play
  - the grievance page: a name, a designation and contact details; complaints acknowledged within 48 hours and resolved within a month (CR32)
  - privacy policy v2, with the purchases row
- **The game:** a Help entry in Settings that links to them.
- **Play Console:** the product list equals `Catalogue` (H4-8); Data safety updated as Q18 found.
- **Release:** staged, as in Phase 32.

OUT OF SCOPE: Ads.

EDGE CASES: MN-6 (refund wording), ST-2.

TESTS: USER: the Help entry and its pages.

READS: MONETISATION §8; PRD CR28, CR32, Q18, Q20, §8.5; RESEARCH L-7, L-9, L-14.

DONE WHEN:
1. PRODUCT — the terms, refund policy, grievance page and privacy policy v2 are live → the URLs and 360-px screenshots.
2. USER — Settings → Help opens them → screenshots; all 16 answers yes.
3. PRODUCT — Play Console's products equal `Catalogue`, and Data safety matches the build → screenshots (H4-8, H4-17).
4. PRODUCT — MONETISATION §7 check 12; the staged release reaches 100% with vitals under Play's thresholds → screenshots.

ASK FIRST IF: ADV-3 says the grievance page must show a phone number (Q15).

DEPENDS ON: Phase 37; Phase 34 (the advisers' answers).

PLAN MODE: Yes: payments and data statements.

PROGRESS:

## Phase 39 — Spike: the ads SDK before consent

STATUS: NOT STARTED

GOAL: Know whether Google's ads SDK starts anything, or sends anything, before `initialize()` is called (LLD U16). Only if ads are ever built.

BUILD (on branch `spike/ads`, thrown away):
- Add the SDK to a test build; list the providers it merges into the manifest; set its delayed-start flags.
- On a fresh install, launch without tapping an ad, and read `dumpsys netstats` for the app.
- Write the answers into LLD §10 and TRD TD-16.

OUT OF SCOPE: Any shipped ad code.

EDGE CASES: HO-6.

TESTS: None kept.

READS: PRD DR30, CR19–CR27, §8.5 (the ads rows); TRD TD-16, §3.4; EDGE-CASES HO-6.

DONE WHEN:
1. LLD §10's U16 row carries a dated answer with its evidence: the merged manifest's providers, and the network bytes for the app before consent.

ASK FIRST IF: Before starting: the ads SDK is a download, and ads ship only past about 3,000 installs a month (Q19).

DEPENDS ON: Phase 33; installs past about 3,000 a month for two months in a row (Q19).

PLAN MODE: No: a spike.

PROGRESS:

## Phase 40 — Ads 1: the age screen and consent

STATUS: NOT STARTED

GOAL: The first tap on an ad offer asks the year of birth neutrally. Adults then see the consent notice: Allow & watch, or Not now. Anyone under 18, or who doesn't answer, never starts the SDK, and gets Periyappa's tip instead.

BUILD:
- **`ads.AdGate`:** adult, plus consent, plus the current notice version (DR30).
- **`ads.Consent`:**
  - kept on the phone, never backed up (DR35)
  - withdrawn from Settings → Ads & privacy, taking effect at once (DR34)
- **The age screen,** opening with "Ads need an age check first." (CR19). It keeps only adult or minor, and the date.
- **The yearly "minor" re-check.**
- **The gate is read at the tap** (GT-1).

OUT OF SCOPE: The placements (Phase 41); Your data, the policy and notices (Phase 42). Phases 40–42 ship in **one** release (CR25).

EDGE CASES: GT-1, HO-6.

TESTS: CODE: every combination of the gate (IDEA H4-2); consent states (H4-3). INTEGRATION: `FakeAds` records no start for a minor. USER: the age and consent screens.

READS: PRD §6 (later screens: the age screen and consent), DR30, DR34, DR35, CR19, CR20; IDEA H2-2, H2-3, H4-2, H4-3; APP-FLOW §1.4; EDGE-CASES GT-1.

DONE WHEN:
1. CODE — `./gradlew check` passes, covering every combination of the gate → the output.
2. INTEGRATION — a 2010 birth year: `FakeAds` records no start, and the offer becomes Periyappa's tip. Allow, then withdraw: no loads → the test report.
3. USER — the age screen, the consent notice, Ads & privacy, and the tip → screenshots; all 16 answers yes.

ASK FIRST IF: ADV-1 (a privacy lawyer, a paid adviser) hasn't checked the wording by 13 May 2027 (CR26).

DEPENDS ON: Phase 39.

PLAN MODE: Yes: user data, consent, and minors.

PROGRESS:

## Phase 41 — Ads 2: rewarded placements, the cap and AdMob's console

STATUS: NOT STARTED

GOAL: Ads appear only as offers the player taps: an extra peek, or reopening a haggle. There are at most 12 a day, a reward comes only for a completed ad, and the game saves before every ad. AdMob's console blocks what it must.

BUILD:
- **`ads.AdCap`:** 12 a day, kept across clock changes (DR33, MN-3).
- **`ads.RewardNonce`:** a reward once, only for a completed ad; a reward that arrives after its moment has passed is dropped (DR32, HO-5).
- **Save before every ad.**
- **"No ad right now"** after 10 s.
- **The owner, in the AdMob console:** blocks gambling, real-money-game and loan ads; sets the maximum rating to "T"; tries frequency capping (Q17, CR23).
- **Testers' phones are AdMob test devices** (TD-32, MN-1).
- **`checkAdIds`:** non-release builds hold only test IDs (H4-4).

OUT OF SCOPE: Interstitials (never); mediation (never).

EDGE CASES: MN-1, MN-3, HO-5, AB-3.

TESTS: CODE: the 12th ad plays and the 13th is refused, across a clock change (H4-6); rewards (H4-5). INTEGRATION: `FakeAds`: no fill, closed early, killed mid-ad. USER: the offers in each state.

READS: PRD DR31–DR33, CR22–CR24; IDEA H1-6, H1-7, H4-4 to H4-7; MONETISATION §2 (model 2); EDGE-CASES MN-1, MN-3, HO-5, AB-3.

DONE WHEN:
1. CODE — `./gradlew check` passes, with the cap and reward tests and `checkAdIds` → the output.
2. INTEGRATION — killed mid-ad, nothing is granted and nothing is lost; closed early, no reward → the test report, and screenshots from the device.
3. USER — each offer: available, "No ad right now", and capped for the day → screenshots; all 16 answers yes.
4. PRODUCT — the AdMob console's blocked categories and "T" rating (H4-7); testers' phones registered as test devices → screenshots.

ASK FIRST IF: AdMob's console can't block the categories the law requires (X4).

DEPENDS ON: Phase 40.

PLAN MODE: Yes: user data and money.

PROGRESS:

## Phase 42 — Ads 3: Your data, privacy policy v2, the notices, Data safety, and the release

STATUS: NOT STARTED

GOAL: The release with ads ships everything the law and Play require, in the same release as the SDK.

BUILD:
- **Settings → Your data:** everything kept on the phone, with Share and Start over (H2-5).
- **Privacy policy v2** (CR25).
- **The ads notice** as Tamil and Hindi pages (CR21).
- **`app-ads.txt`** at the site's root.
- **Data safety** declares Google Mobile Ads, and the ad ID becomes "Yes".
- **The permission allowlist** gains `INTERNET`, `ACCESS_NETWORK_STATE` and `AD_ID`.
- **The release:** one release, staged.

OUT OF SCOPE: More ad formats; more countries (CR27).

EDGE CASES: ST-2, HO-6.

TESTS: CODE: the extended allowlist. USER: Your data. PRODUCT: the release checklist.

READS: PRD CR21, CR25–CR27, §8.5; IDEA H2-1, H2-5, H4-17; RESEARCH L-14.

DONE WHEN:
1. PRODUCT — privacy policy v2, the Tamil and Hindi notices and `app-ads.txt` are live → the URLs and screenshots.
2. USER — Your data → screenshots; all 16 answers yes.
3. PRODUCT — the merged manifest, Data safety and PRD §8.5 agree (H4-17), and the staged release reaches 100% with vitals under Play's thresholds → screenshots.

ASK FIRST IF: Anything in the data table must change beyond the ads rows.

DEPENDS ON: Phase 41.

PLAN MODE: Yes: data statements.

PROGRESS:

---

## CURRENT STATE

**Updated 2026-11-26, with the owner's decisions before Phase 6.**

**Built so far:**
- **The planning docs:** PRD, TRD, APP-FLOW, DESIGN, EDGE-CASES, MONETISATION, LLD, and this plan.
- **The empty project:**
  - `logic`, `core`, `android` and `lwjgl3`
  - `./gradlew check` passes; it runs on the Android 16 emulator, and screenshots work (CLAUDE.md)
  - the javac lint is JDK-aware (BR-3), verified on JDK 17 and JDK 21
- **The waitlist test is live:** the page at https://rameshkumark24.github.io/bazaar-boss/, and the Google Form behind its button. Nothing is posted yet, so the seven days haven't started.
- **Phase 4's answers:**
  - back on Android 13+ goes through the launcher's own callback, because libGDX's route stalls when drawing on demand (TD-35)
  - the insets come from Android's `WindowInsets`, not libGDX's
  - drawing on demand works
  - the desktop window works
- **Phase 3's answers:**
  - text is drawn from distance-field atlases baked at build time, one per face (TD-36), because bitmaps baked at build time are either soft or tied to one density
  - all three faces have ₹ and the punctuation the game needs, and Roboto's digits are already tabular
  - Android writes non-Latin digits under Marathi and other locales, as the JDK does, so the locale guard stands (U9)
- **Phase 5's answers:**
  - the save's JSON is fast enough when warm (50 KB encodes at p95 20 ms on `LowEnd_A9`), and longs are exact
  - a commit takes p95 8–20 ms on the emulators
  - **libGDX calls no `dispose()` on a rebuild,** so the writer drains in `pause()`, at most 2 s (TD-38)
  - fixtures install through `run-as` (CLAUDE.md)

**Phase status:**
- **Phases 3, 4 and 5 are DONE.**
  - Phase 4, and Phase 3 apart from its handoff note, are in `main`.
  - The rest merges on 2026-11-26, at the owner's word: Phase 3's handoff note, the first doc check with its code review, and Phase 5.
- **Phases 1 and 2 (owner) are IN PROGRESS.** Phase 1 waits for the posts and day 7.
- **Phase 6 is IN PROGRESS** on branch `phase-6-vertical-slice`. Its plan, in its PROGRESS, waits for the owner's approval (PLAN MODE yes).
- Every other phase is NOT STARTED.

**Next:**
- **The build has started before day 7,** by the owner's decision on 2026-11-25 (PRD Q1). On day 7 the thresholds apply to the build in progress.
- **The owner:** approve or change Phase 6's plan, including its decisions A–F.
- **Agent:** build Phase 6 once its plan is approved: seven checkpoints, about 4 sessions. Every later phase depends on it.
- **Phase 1 (owner):** confirm that the haggle plays in all three voices on the phone; record and post the clip, and send the date of the first post; on day 7, send the counts and each post's views.
- **Phase 2 (owner):** the rest of the checklist in Phase 2's PROGRESS.
- **The waitlist page** has K4's colour fix in its source. It goes live at the next publish, on the owner's word.
- **Decided before Phase 6 (the owner, 2026-11-26), all as recommended:**
  - Phase 3's DONE WHEN 1 is reworded. The method is in TD-36, which corrects TD-26, so Phase 3 is DONE.
  - Material Symbols: download it
  - DESIGN §2.2's font sentence stays, matching TD-36
  - the waitlist page's coins: the favicon is `scene-brass`, and the demo's top-bar coin is `text-muted` (K4)
  - DESIGN §10 gains question 16, on text sharpness. Every check that counted 15 answers now counts 16.
  - the merge order: Phase 3's handoff note, the doc check, then Phase 5
- **Decided after Phase 4 (the owner, 2026-11-25):**
  - the offer slider's target stays 32 dp from the screen edges, and is excluded from the back gesture (DESIGN §2.7)
  - Phase 3's downloads are a yes
  - Phases 1 and 4 merge into `main`

**Sessions, used against the estimate:**

| Group | Estimate | Used | Status |
|---|---|---|---|
| Before M1 | 0 build sessions; 7 days | 1: the agent made the form and published the page, at the owner's request | Phase 1 in progress; the 7 days start with the first post |
| M1 (Phases 3–12) | 9 | 3: Phases 4, 3 and 5 | Phases 3, 4 and 5 done; Phase 6 next, its plan first |
| M2 (Phases 13–29) | 17 | 0 | — |
| M3 (Phases 30–32) | 5 | 0 | — |
| After M3 (33–42) | 4 purchases + 4 ads | 0 | Behind G3 and Q19 |

**Open questions that stop a phase until they're decided:**
- Q1's day-7 count: it decides whether the build in progress goes on
- Q8, bohni (Phase 9)
- Q4 and Q5 (Phase 15)
- Q7 (Phase 19)
- Q9's numbers (Phases 20 and 29)
- Q2, the reader (Phases 2, 26, 27)
- Q16 (Phases 7 and 30)
- Q3 (Phase 31)
- Q18 (Phase 36)
- Q17 (Phase 41)

---

## KNOWN ISSUES

Bugs we choose to live with, and security findings left unfixed, go here, so nothing lives only in a chat:

| # | Issue | Severity | Found in | Why we live with it | Revisit when |
|---|---|---|---|---|---|
| K1 | Android Lint warns `MissingApplicationIcon` | Low | The empty project (2026-11-24); listed in CLAUDE.md | The app icon comes with the art, drawn as shapes in code | Phase 30 adds the icon and clears it |
| K2 | `android/build.gradle`'s natives task copies only the **first** natives jar for each ABI. A second native library would be dropped silently and crash at runtime | Low, latent | Phase 3 (2026-11-25) | libGDX is the only native library, since FreeType stays at build time (TD-36) | A second native library is added: collect every natives jar for the ABI with `findAll`, then copy them in **one** `fs.sync` (`jars.each { from archives.zipTree(it) }`). One `fs.sync` per jar deletes the earlier jars' `.so` files (shown in the code review, 2026-11-25) |
| K3 | libGDX 1.14.2's own back callback (Android 13+) asks for no frame, so back stalls when drawing on demand | Medium, worked around | Phase 4 (2026-11-25) | The launcher's own callback routes back instead (TD-35) | A libGDX release asks for a frame in its callback; then the workaround can go |
| K4 | ~~The waitlist page fills two coins with marigold (`accent`)~~ **Fixed in the source 2026-11-26, on the owner's word:** the favicon is `scene-brass` (#cda448, a coin drawn as illustration) and the demo's top-bar coin is `text-muted` (#b6becb, an icon, DESIGN §2.5). Only the Join button is marigold now | Low | The code review (2026-11-25) | The live page keeps the old colours until it's published again | The page's next publish, which needs the owner's word |
| K5 | ~~TRD TD-28 still says the writer is "drained on dispose"~~ **Resolved 2026-11-25:** TRD TD-38 corrects it. Phase 5 found no `dispose()` on a rebuild, and the drain is in `pause()` | — | The code review (2026-11-25) | — | — |

---

## COVERAGE CHECK

### PRD features and their acceptance criteria

| Feature | Criteria → phase |
|---|---|
| F1 First launch, voice picker | AC1–AC5 → 22 |
| F2 The haggle | AC1–AC5, AC10 (buying) → 6 · AC6–AC10 (both directions), AC8 → 9 · AC2 (face) → 11 |
| F3 Customers | AC1 → 9 (simulator) · AC2 → 20 · AC3 → 11 · AC4 → 26 |
| F4 Items, inspection, fakes | AC1–AC4 → 15 |
| F5 Auction, reveal | AC1–AC5, AC7, AC8 → 17 · AC6, AC9, AC10 → 18 |
| F6 The market day, the stall | AC1–AC3, AC5, AC7, AC8 → 14 · AC4, AC6 → 20 |
| F7 Growth | AC1–AC4 → 19 · AC5 → 29 (prediction) and 31 (measured, Check 3) |
| F8 Reputation | AC1, AC2, AC5 → 9 · AC3, AC4 → 20 |
| F9 The godown | AC1–AC4 → 18 |
| F10 The kanakku | AC1–AC5 → 19 |
| F11 The floor | AC1–AC5 → 20 |
| F12 The deal card | AC1–AC3 → 21 |
| F13 Voices, text, numbers | AC1 → 8 · AC2 → 6 · AC3 → 22, 26 · AC4 → 27 · AC5 (no banknotes) → 21 |
| F14 Sound and haptics | AC1–AC4 → 23 |
| F15 Saves, resume, back | AC1 → 6, 23 · AC2–AC4, AC7 → 13 · AC5 → 25 · AC6 → 14, 23 · AC8 (owed results) → 6, 17, 19, 23 |
| F16 Settings | AC2, AC4 → 7 · AC1–AC4 → 22 |
| F17 Start over | AC1–AC2 → 25 |
| F18 Share my stats | AC1–AC3 → 21 |
| F19 Reports, crash notes | AC1–AC5 → 25 |
| F20 Offline, small, light | AC1 (airplane mode), AC2, AC4, AC5 → 30 (the release build) · AC3 → 11, 29 · AC6 → 28, 29 |
| F21 The shop | AC4–AC8 → 36 · AC1–AC3, AC9, AC10 → 37 |

### APP-FLOW screens

| Screen | Phase |
|---|---|
| 1 Voice picker | 22 |
| 2 Stall: morning, trading, evening, broke | 14; 19, 20 |
| 3 Haggle | 6, 9, 11 |
| 4 Inspect | 15 |
| 5 Auction | 17 |
| 6a Reveal · 6b Sort · 7 Godown | 18 |
| 8 Kanakku | 19 |
| 9 Settings · Credits & licences | 7 (minimal); 22 |
| Deal result · Pause | 6 |
| Tier-up | 19 |
| Low cash | 20 |
| Leave the game? | 14 |
| Leave the auction? | 17 |
| Hints | 22 |
| Share my stats preview | 21 |
| Report a problem · Report this line · Start over | 25 |
| Save recovered · Couldn't read any save · Newer save · Restored from backup | 13; 25 (the restore check) |
| Dev: text test | 8 |
| Dev: haggle lab | 11 |
| 24 Shop | 37 |
| 25 Ad offers | 41 |
| 26 Age + consent | 40 |
| 35 Ads & privacy · Your data | 40, 42 |
| 11–14, 16, 17, 19, 21, 22, 27, 31 (after-G3 screens) | **Not in this plan:** a new plan after G3 |

### MONETISATION

- **The paywall moment:** one "See the shop" link on the first Tier-up card → Phase 37.
- **Its payment tests** (MONETISATION §7): 1–7 → Phase 36; 8–11 → Phase 37; 12 → Phase 38.
- **The refund terms** (Q20) → Phase 38.
- **The advisers and the merchant account** → Phase 34.
- **The ads model,** only past ~3,000 installs a month → Phases 39–42.

### DESIGN

| Rule | Phase |
|---|---|
| Components 1–4, 6–10 (Button, chip, slider, price tag, speech line, meter, panel, modal card, banner) | 6 |
| Component 5, the face | 11 |
| Components 11–12 (list row, empty state) | 18 |
| Component 13, the skeleton | 37 |
| Scales and palette | 6, with the palette guard |
| Motion: transitions | 6, 14 |
| Motion: the Reveal | 18 |
| Motion: reduced | 11, 18, 23 |
| The five states | Every phase's USER check |
| Accessibility: XL on 316 dp | Every USER check |
| Accessibility: TalkBack's announcement | 23 |
| Words | 8 (validator), 26–27 |
| Assets: fonts | 3, 6 |
| Assets: icons | 6 |
| Assets: sounds | 23 |
| Assets: app icon, store images | 30 |
| §10, the visual checklist | Every USER check |

### The top ten edge cases (EDGE-CASES' ranking)

| Rank | Case | Phase |
|---|---|---|
| 1 | SV-1 | 6, 30 |
| 2 | DV-1 | 3, 6 |
| 3 | LC-3 | 6, 9, 17, 19, 23 |
| 4 | LC-2 | 5, 6 |
| 5 | IN-1 | 6 |
| 6 | HE-1 | 6, 9 |
| 7 | DV-2 | 11 |
| 8 | MS-2 | 21, 31 |
| 9 | ST-1 | 2, 31 |
| 10 | MN-1 | 41 |

### The unknowns in LLD §10

| Unknown | Phase |
|---|---|
| U3, U9 | 3 |
| U1, U2, U4, U19 | 4 |
| U5–U8 | 5 |
| U12, U18 | 10 |
| U10 | 12 (G1) |
| U11 | 16 |
| U13, U14 | 24 |
| U17 | 28 |
| U15 | 35 |
| U16 | 39 |

### Gaps: what this plan doesn't build

- **After-G3 content:**
  - restoration and its timers (H4-14); assistants; offline income
  - more cities and seasons; collections and the Dealer's Guide; upgrades
  - rival bluffing; godown auctions; notifications (DR39)

  PRD §5 puts them after G3. They need their own plan then.
- **Cloud save and achievements:** blocked until the data table is redone (PRD §5).
- **Scripted journeys with Maestro:** they wait for your yes. Until then, USER checks run on `adb` and taps by hand.
- **Q10** (native-script menus) and **Q11** (the old engine's code) need no phase: the answers are "no" and "write it fresh".

Every PRD feature, every v1 screen, the paywall and its tests, the DESIGN rules, the top ten edge cases and every LLD unknown land in a named phase.
