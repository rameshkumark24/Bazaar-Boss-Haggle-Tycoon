# Bazaar Boss: Haggle Tycoon — App Flow and User Flow

> **What this is:** two maps of how the game works as an experience.
> - **Part 1** is the structure: every screen, how it's reached, what back does, and its states.
> - **Part 2** is the experience: the three journeys that matter most, step by step, including where each one breaks.
>
> **Built from:** [PRD.md](PRD.md) (§3 core loop, §4 features, §6 screens, §7 rules) and [TRD.md](TRD.md) (§7 auth and deep links). Written 2026-11-24.
>
> **It changed the PRD.** The screen changes are already in PRD §4, §6 and §7, and listed in [§5 below](#5-what-this-changed-in-the-prd). The PRD stays the source of truth; this file explains why.
>
> **Labels:** [A] = assumption, tuned in play-testing · [Q#] = an open question in PRD §10 · "later" = not in v1.

---

## Part 1 — The app flow (structure)

### 1.1 How navigation works here

**One hub, no tabs.** The stall is home, and every v1 screen is one step from it. There's no tab bar, for three reasons:
- **A market day is a sequence,** not parallel areas: morning stock → customers → kanakku. Tabs suggest you can be anywhere at any time.
- **Only one haggle at a time** (PRD §7.10). A tab bar would invite jumping out of a haggle mid-way, leaving the customer frozen.
- **A portrait phone game needs the screen** for the face, the item and the price. A permanent tab bar costs about 10% of the height.

**Three kinds of move, each with one job:**

| Move | Used for | Back | Example |
|---|---|---|---|
| **Push** — full screen | **Places:** somewhere you go and spend time | Returns to where you came from | Godown, Settings, Auction, Haggle |
| **Modal** — a card over the current screen | **Moments:** a result, a choice, a warning. **Never more than one at a time** | Takes the safe choice: Continue, Cancel or Keep | Deal result, Tier-up, Low cash, Sort's scrap confirmation |
| **Hand-off** — leaves the game | Anything another app does better | Android brings you back to the same screen | Share sheet, email app, browser, Play listing |

**Two always-available controls:**
- **Settings (gear):** on every screen. Opening it pauses a haggle or an auction (F16).
- **Mute:** on the voice picker and the stall (F14).

### 1.2 The navigation tree — v1

```
App icon
└─ Launch routing (§1.3)
   ├─ First launch ─► Voice picker ─► FIRST HAGGLE (a patient walk-in seller) ─► Deal result ─► Stall (first time)
   └─ Any later launch ─► exactly where the save says (Stall, a haggle in progress, a paused auction, the Reveal, the Sort list or the kanakku)

Stall  (hub — morning, trading or evening state)
├─ Morning: "Go to the auction" ─► Auction: peek (10 s) ─► bidding ─► result with rivals' estimates
│                                       ├─ won ─► Reveal (bag empties itself; tap to speed up) ─► Sort (all Keep by default) ─► Stall
│                                       └─ lost / skipped / left ─► Stall (walk-in sellers come instead)
├─ Today's special card ─► a special visitor (─► Haggle) or a special lot (─► Auction)
├─ A waiting customer ─► Haggle
│     ├─ Inspect (an item, close up) ─► back to Haggle
│     ├─ deal ─► Deal result (modal) ─► [Share ─► Android share sheet] ─► Stall
│     ├─ walk away ─► call-back (back into the Haggle) or Lesson line ─► Stall
│     └─ back ─► Pause (modal: Resume · Settings)
├─ Godown ─► Inspect · put on display · Scrap (confirmation)
├─ While broke: "Periyappa can help" ─► Low cash (modal: loan · scrap · cheap-seller day)
├─ The last customer leaves ─► Day end (kanakku) ─► [Upgrade ─► Tier-up (modal)] ─► Stall (evening) ─► "Start Day N+1" ─► Stall (morning)
├─ Gear ─► Settings
│     ├─ Voice · Sound · Vibration · Text size
│     ├─ Privacy policy ─► browser (hand-off)
│     ├─ Credits & licences (push)
│     ├─ Share my stats ─► preview (modal) ─► Android share sheet
│     ├─ Report a problem ─► reason + the whole message (modal) ─► email app
│     └─ Start over ─► confirm ─► confirm again ─► Voice picker
└─ back ─► "Leave the game?" (modal: Stay · Leave)

Anywhere: long-press a line of dialogue ─► Report this line (modal) ─► email app
```

**Later screens attach here:**
- **Stall:** Workshop, Market map, Upgrades, Shop, Dealer's Guide, Reputation detail.
- **Settings:** Help, Ads & privacy, Your data.
- **Contextual:** Ad offers (beside the thing they speed up), the Age screen + consent (the first ad tap), the Notification explainer (the first restoration), Welcome back (opening the game after time away), Event card (the first day of a season), Rate prompt (once, after a big win).

### 1.3 Launch routing — what the first frame is

| On launch, the save is… | The player sees | Traces to |
|---|---|---|
| Missing (first launch, or after Start over) | **Voice picker** | F1 |
| Fine | **Exactly where they left off:** stall, a haggle in progress (same round), a paused auction, the reveal, the Sort list, or the kanakku | F15 AC1, DR12 |
| Damaged, but a backup is good | A **"Restored your backup from <time>"** card, then where that backup was | F15 AC3 |
| Unreadable, with every backup too | A **"Couldn't read your save or its backups, so a new game has started."** card. The broken files are kept for a report, and Report a problem is prefilled with "lost progress" | TRD §4.10 |
| From a **newer** version of the game | A **block** card: "This save is from a newer version. Update the game to keep playing.", with **Update on Play** and **Write to us**. No play is possible, so the save can't be overwritten | F15 AC7 |
| Restored onto a new phone by Android's backup | **"Welcome back. Your progress from <last played date> is here."**, once [A], then where it was. *Why:* a backup can be up to a day old; without this card, a missing day looks like a bug | F15 AC5; H5-2 |
| Fine, but the phone's date went back 1–2 days | Normal; Today's special shows "back tomorrow" | DR26 |

### 1.4 Auth — and the gates that stand in for it

**No screen needs auth:** there are no accounts, sign-in or tokens (PRD §5 "Online"; TRD §7). The things a player can "hit" before they're allowed are gates:

| Gate | Guards | Someone who hits it… |
|---|---|---|
| **First launch** | Everything except the voice picker | …picks a voice. That's the whole gate, one tap |
| **Newer save** | The save, from being overwritten by an old build | …updates the game, or writes to us. They can't play until then (§1.3) |
| *M4:* **ad gate** — adult + consent + current notice (DR30) | The ads SDK | …at the first ad tap, sees the age screen and then, if adult, the notice. A minor, or someone who doesn't answer, gets Periyappa's tip instead, and nothing in the game is locked |
| *Purchases:* **billing reachable** | The shop | …sees "Can't reach Google Play" and their owned cosmetics still work (TRD §4.6) |

### 1.5 Deep links and cold starts

**There are no deep links in v1.** The manifest's only entry point is the launcher (TRD §7). So a "cold start into a protected screen with an expired token" can't happen. These are the cold starts that do:

| Cold start… | What happens |
|---|---|
| After Android killed the game mid-haggle | The haggle comes back at the same round: same customer, offers, mood and patience (F15 AC1) |
| …mid-auction | The auction comes back **paused**. Nobody bid while the game was gone (F5 AC8) |
| …mid-reveal | The items already out are in the godown, and the rest come out on return (F5) |
| …on the kanakku | The kanakku again. The day stays finished |
| *Later:* from a notification tap | See §1.6 |

### 1.6 Notifications (later — they arrive with restoration, after G3)

v1 has none, and there's never a push service (DR39). When local notifications arrive, they land like this:

| The game is… | The tap lands… | Why |
|---|---|---|
| Closed | Cold start → the save loads → **the Workshop** if the item still exists. If it doesn't: **the stall**, with "That item's gone" (IDEA §4) | No dead link |
| In the background, on another screen | **Where the player was.** A banner offers "Restoration done. See the workshop" | A tap **never interrupts a haggle or an auction**. A customer mid-deal must not vanish because of a notification |
| **Open** | **No notification is posted at all.** The game shows the same banner in place | A system notification about the app you're looking at is noise |

### 1.7 What back does, on every screen

The rule: **back never does anything irreversible, and never quits by accident.** It takes the safe choice.

| Screen | Back does | Why |
|---|---|---|
| **Voice picker** (first launch) | Leaves the app, with no confirmation | There's no game yet, so nothing is lost |
| **First haggle** (before the stall exists) | Pause (Resume · Settings) | Leaving would strand a new player with no hub |
| Stall | "Leave the game?" — Stay · Leave | F15 AC6 |
| Haggle | Pause — never a walk-away | PRD §7.7 |
| Inspect | Back to the haggle or the godown it came from | — |
| Auction, during the peek or bidding | "Leave the auction?" Leaving means not winning, and nothing is paid | DR18 |
| Auction, after the hammer | Stall | — |
| **Reveal** | **Reveals everything at once.** No decision is made for the player | Back means "hurry", not "throw away" |
| **Sort** | **Keeps everything** and returns to the stall | Keeping is reversible — scrap later from the godown. Scrapping isn't |
| Godown | Stall | — |
| Day end (kanakku) | The stall's evening state. The day stays finished | You can't un-end a day |
| Settings, and its sub-pages | One level up, then back to the screen Settings was opened from, still paused | — |
| Any modal | Its safe choice: Continue (results), Cancel (confirmations), Keep (Sort), Close (previews and reports — nothing sent) | — |
| Newer-save block | Leaves the app | Nothing else is possible until an update |
| Pause | Resume | — |

### 1.8 ⭐ The table — one row per screen

**The columns:**
- **Auth:** "none" everywhere — there are no accounts (§1.4).
- **States:** **L** = loading · **E** = empty · **Er** = error · **O** = offline.
- **v1 is offline by design.** "O: n/a" means nothing changes without a network.
- **Phase:** the build slice from the Business verdict §3 (17 slices to v1). After v1: M4, after G3, or purchases.

| # | Screen | Auth / gate | Reached by | States | If its item is gone | Phase |
|---|---|---|---|---|---|---|
| 1 | Voice picker | none — the first-launch gate itself | Launch routing (first launch, after Start over) | L: no (content loads behind the splash) · E: — · Er: a voice line missing → English (F1) · O: n/a | — | 12 |
| 2 | Stall (hub) — morning, trading, evening; "broke" | none | Launch routing; back from places; after the first deal | L: no · **E:** first visit has no stock and empty display slots marked "Empty" · Er: "Couldn't save. Phone storage is full." card (F15 AC4) · O: n/a | A display slot whose item was sold shows "Empty" | 10 (hub, goal bar), 11 (Today's special, teaser) |
| 3 | Haggle | none | Push: a waiting customer; the first launch; a special visitor | L: no · E: never opens without a customer and an item · Er: save failure card; play goes on · O: n/a | The item is reserved while the haggle lasts (TRD §4.8), so it can't go. If the customer left, it returns to the stall | 6 (screen), 4–5 (engine, personalities) |
| 4 | Inspect (one item, close up) | none | Push: from the haggle or the godown | L: no · E: clues not yet found read "Look closer" [A] · Er: — · O: n/a | "That item's gone", then back | 7 |
| 5 | Auction (peek → bidding → result) | none | Push: the stall's morning card; a special lot | L: no · E: today's auction done → "Next auction: tomorrow morning" · Er: too little cash → peek plays, bid says "Not enough cash", **Skip** stays (F5) · O: n/a | — (a lot is fixed before the peek) | 8 |
| 6a | **Reveal** *(split from "Lot reveal & sort")* | none | Push: after winning an auction | L: no · E: a lot is never empty [A] · Er: killed mid-reveal → resumes (F5) · O: n/a | — | 9 |
| 6b | **Sort** *(split)* | none | Push: after the Reveal | L: no · E: an all-junk lot shows "Scrap all for ₹X" · Er: — · O: n/a | — | 9 |
| 7 | Godown | none | Push: from the stall | L: no · **E: two kinds** — never had items / filtered to nothing (F9) · Er: — · O: n/a | Sold or scrapped items simply aren't listed | 9 |
| 8 | Day end (kanakku) | none | Automatic, after the last customer | L: no · E: "No deals today", still followed by tomorrow's teaser (F10) · Er: — · O: n/a | Deals are stored as copies (TRD §4.5), so they always show | 11 |
| 9 | Settings | none | Gear, from every screen | L: no · E: Share my stats shows zeros before any play · Er: no browser → the policy address is shown (F16) · O: the privacy page needs the internet in the *browser*; the game itself doesn't | — | 12 |
| — | Credits & licences | none | Push: Settings | static | — | 12 |
| — | Deal result | none | Modal: a deal closes | never empty | Shows the stored copy of the deal | 6 (verdict), 11 (share card) |
| — | Tier-up | none | Modal: Upgrade on the kanakku | — | — | 10 |
| — | Low cash | none | Modal: when broke; and **"Periyappa can help" on the stall while broke** | — | — | 10 |
| — | Pause | none | Modal: back in the haggle or auction | — | — | 12 |
| — | Leave the game? / Leave the auction? | none | Modal: back | — | — | 12 |
| — | Hints | none | Inline, the first time each thing appears | — | — | 12 |
| — | Share my stats preview | none | Modal: Settings | E: zeros · Er: "Couldn't share" | — | 12 |
| — | Report a problem / Report this line | none | Modal: Settings; long-press a line | Er: no email app → the address is shown (F19 AC5). The crash-note option appears only when a note exists | — | 15 |
| — | Start over (2 confirmations) | none | Modal: Settings | — | — | 15 |
| — | Save recovered · Couldn't read any save · **Newer save (block)** · **Restored from backup** [A] | none | Launch routing (§1.3) | They *are* the error states | — | 3 |
| — | Dev: text and content test · Dev: haggle lab | development builds only | Dev menu | — | — | 1–3 · 6 |
| 11 | Workshop | none | Push: stall; a notification | E: no slots in use · Er: — · O: n/a | Restoration item gone → stall, with a note (§1.6) | after G3 |
| 12 | Assistants | none | Push: stall | E: none hired | — | after G3 |
| 13 | Welcome back (what finished while away) | none | Launch, after time away | E: nothing finished → skipped | — | after G3 |
| 14 | Upgrades | none | Push: stall, kanakku | — | — | after G3 |
| 16 | Market map | none | Push: stall | — | — | after G3 |
| 17 | Event card | none | Modal: the first day of a season | — | — | after G3 |
| 19 | Dealer's Guide **(with Collections as sections)** | none | Push: godown | **E: two kinds** — never found / filtered | Retired items stay listed (content is never deleted, TRD §6) | after G3 |
| 21 | Reputation detail | none | Push: tap reputation on the stall | — | — | after G3 |
| 22 | Help | none | Push: Settings | static | — | after G3 |
| 25 | Ad offers | none | Inline, beside what they speed up | Er: **"No ad right now"** (O: the same) | — | M4 |
| 26 | Age screen + ads consent | **the ad gate itself** | Modal: the first tap on an ad offer | Er: — | — | M4 |
| 35 | Ads & privacy · Your data | none | Push: Settings | E: Your data lists only what exists | — | M4 |
| 27 | Notification explainer | none | Modal: the first restoration started (Android 13+) | — | — | after G3 |
| 24 | Shop | none — **billing-reachable gate** | Push: stall or Settings | L: product details from Play (a spinner) · Er/O: "Can't reach Google Play"; owned cosmetics keep working · E: — | A product removed from Play is hidden. Owned ones stay restorable (product IDs are permanent, TRD §6) | purchases |
| 31 | Rate prompt | none | Google's own sheet, once, after a big win | — | — | after release |
| ~~20~~ | ~~Item detail~~ | — | **Merged into Inspect** (§4, 2) | — | — | — |
| ~~18~~ | ~~Collections~~ | — | **Merged into the Dealer's Guide** (§4, 2) | — | — | — |

---

## Part 2 — The user flow (experience)

The three journeys that matter most, in order:
1. **First-ever open** — players who don't get a deal in the first minute never come back.
2. **A market day** — the loop the whole game rests on.
3. **Coming back the next day** — the moment PRD Check 1 measures.

### 2.1 Journey 1 — first-ever open → the first deal

**Target:** the first offer within 30 seconds (F1 AC3), and the first deal within 60 seconds (F1 AC4).

| Step | They see | They must decide | What could confuse them | Where they could fail → what happens |
|---|---|---|---|---|
| 0 | The app icon → Android's launch splash, then the voice picker (cold start ≤ 4 s [A]) | — | A slow first start on a cheap phone. The splash shows a default icon until the art arrives | — |
| 1 | **Voice picker:** English, Tanglish, Hinglish, each with a sample line; "Change it any time in Settings"; mute | Which voice | "What's Tanglish?" — the sample line answers it | They pick a voice they read badly → Settings → Voice changes it at once (F1 AC5). Back here quits, losing nothing |
| 2 | **Tap 1 → the first haggle, at once.** A walk-in seller at your stall with a brass lamp, asking ₹400. Her face and mood word ("Hopeful"), patience dots, and one hint: "Sellers start high. Try −25%." | What to offer | **Is ₹ real money?** The first hint says "All ₹ here is game money." (§4, 3). What the patience dots mean — a one-line hint the first time | Nothing yet |
| 3 | **Tap 2: −25%** — the offer is made in one tap. She counters "₹350. Last, I swear!", her mood moves, and one line says why | Accept, or push further | The verdict hasn't appeared yet, so they can't tell whether ₹350 is good | **They lowball hard:** the first seller is patient and won't leave before round 3 [A]. **They walk away:** she calls back once ("Okay okay, vaanga!"). **She does leave:** a second walk-in seller arrives (the floor, F11) — never a dead end |
| 4 | **Tap 3: Accept** → the Deal result: "Bought: brass lamp, ₹350. She'd have taken ₹300." | Continue, or Share | The verdict can read as "I lost ₹50". It's worded as a tip: "Next time, try lower" [A] | — |
| 5 | Continue → **the stall, for the first time:** the goal bar at 20% ("Periyappa's gift"), the lamp on display, the first buyer arriving | — | What the bar is for: a one-line hint | — |

**Taps from app-open to the core action done (the first deal): 3** — voice, −25%, Accept.

The old flow took **5**:
- The voice picker opened the stall, and you then tapped the seller.
- Offering took a slider move plus an Offer tap.

**Two cuts made the difference** (both now in the PRD):
- First launch goes **straight into the first haggle** (F1). "A customer is already at the stall" is still true: the haggle happens at your stall.
- The **quick buttons offer in one tap** (F2).

**Where journey 1 breaks:**

| Break | What happens | Status |
|---|---|---|
| A phone call mid-haggle | Resumes at the same round (F15 AC1) | Designed |
| Back pressed in the first haggle | Pause (Resume · Settings). There's no stall to drop into yet | Designed |
| Android's "Viewing full screen" notice covering step 2 | **Removed** — the status bar now stays visible (TRD TD-25) | Fixed 2026-11-24 |
| They think it's a real-money game | First hint: "All ₹ here is game money." | **Applied** (F1) |
| They expect a tutorial and find none | One-line hints only (F1, by design). The patient first seller makes the first haggle safe to fumble | Designed |
| Text too small to read | The default text size is medium. Settings → Text size, but they don't know that yet | Watch at G1 |

### 2.2 Journey 2 — one market day

**Target:** 3–5 minutes [A]: morning stock, 5–8 customers, the kanakku.

| Step | They see | They must decide | What could confuse them | Where they could fail → what happens |
|---|---|---|---|---|
| 1 | **Stall, morning:** "Day 4". Cards for the auction and Today's special | Auction, special, or straight to trading | Whether the auction is required. **It isn't** — "Skip" is equal to "Go" | — |
| 2 | **Auction peek (10 s):** part of the lot; the two rivals' faces | What it's worth | The peek ending mid-thought. There's a countdown, then bidding starts | — |
| 3 | **Bidding:** bid buttons (+₹50 / +₹100 [A]); rivals bid live; a countdown that restarts with each bid | Bid again, or stop | Real-time pressure, the game's only clock. Whether a rival is "bidding me up" — the estimates shown after the hammer answer that (DR19) | **Overbids:** the result shows each rival's estimate — a lesson, not a game over. **Out of cash:** the bid button says "Not enough cash", and Skip stays. **App killed:** the auction resumes paused, with bids kept (DR17). **Back:** "Leave the auction?" |
| 4 | **Reveal** (won): the gunny bag empties itself, item by item | Nothing — it's the show | — | Tapping speeds it up; back reveals everything at once. Killed → it resumes |
| 5 | **Sort:** every item marked Keep, each with its condition and scrap value | Scrap the junk? | Whether a doubtful item is fake — Inspect is one tap from each row | **Scraps something good:** one confirmation lists each item and the total first. **Back:** keeps everything |
| 6 | **Trading:** customers waiting at the stall | Whom to serve first | Why nobody wants to buy. **Auto-display** prevents it: new stock fills empty slots (F6 AC8) | Customers wait — there's no clock (DR12) |
| 7 | **Haggles** (×5–8): tap a customer → offers → accept or walk | Each price | Inspecting mid-haggle costs patience, if Q4's recommendation is accepted | **Sells a fake knowingly:** −reputation, with the reason. **Storage full:** the card shows, and play goes on (F15 AC4) |
| 8 | **Broke** at some point | Loan, scrap, or a cheap-seller day | The loan terms are shown before accepting (F11 AC2) | **Closes the card while still broke:** "Periyappa can help" stays on the stall (§4, 4) |
| 9 | **Kanakku** — automatic after the last customer: best deal first … tomorrow's teaser | Upgrade (when the bar is full) | — | **Leaves the app here:** it returns to the kanakku |
| 10 | **Stall, evening:** "Day 4 done", with the button "Start Day 5" | Play on, or stop | — | It's a natural place to stop. The day ends on a high (DR29) |

**Taps from app-open to the core action done (one sale), resuming mid-day with a buyer waiting: 3** — tap the buyer, −10%, Accept.

**Taps in a whole day:** roughly 30–50. **Most of these taps are play, not overhead:** offers and bids are the game. Navigation, confirmations and dismissals add at most **2 per customer** (tap them; Continue on the result). The **target: overhead ≤ 3 per customer.**

**Where journey 2 breaks:**

| Break | What happens | Status |
|---|---|---|
| **Nothing on display, so no buyers** — "the game is broken" | New stock fills empty display slots automatically; the player can rearrange | **Applied** (F6 AC8) |
| Broke, and the card dismissed | "Periyappa can help" stays on the stall | **Applied** (§4, 4) |
| A spectacle and a decision on one screen: tapping fast through the reveal hits Scrap | The reveal and Sort are now two screens | **Applied** (§4, 1) |
| Killed mid-bid / mid-reveal / mid-haggle | Done once or not at all; resumes (TRD §4.10) | Designed |
| A long day on a slow phone | The day is 5–8 customers; the haggle has no clock | Tune at G1 |

### 2.3 Journey 3 — coming back the next day (or after a gap)

**Target:** the player returns without being pulled (PRD Check 1) and has a deal within the first minute.

| Step | They see | They must decide | What could confuse them | Where they could fail → what happens |
|---|---|---|---|---|
| 1 | Open the app → **exactly where they left:** usually the evening stall, "Day 4 done", with the button "Start Day 5" | — | — | **Save damaged:** "Restored your backup from <time>" — at most since the last day end is lost. **Newer save:** the block card, Update on Play or Write to us |
| 2 | **Tap 1: Start Day 5** → morning: **Today's special** and **the teased customer** ("The collector is here for a brass lamp!") | Serve the teased customer first? | — | **The date went back 1–2 days:** the special reads "back tomorrow". **More than 2 days back:** it's treated as a clock correction (DR26) |
| 3 | After a gap of ≥ 3 days [A]: "Welcome back. Here's a cheap starter lot." (once) | Take it? | — | — |
| 4 | **Tap 2:** serve the collector → **tap 3:** offer → **tap 4:** Accept | — | — | **A new phone:** "Your progress from <date> is here" explains a backup that's a day old |

**Taps from app-open to the core action done: 4** — Start Day, serve, offer, Accept. **That's one more than the target, kept on purpose.** "Start Day" is the session's opening ritual: the shutter goes up, and yesterday's teaser pays off (the open loop, IDEA §3.2 #4). Skipping it would blur one day into the next, and the peak-end design needs days to have edges. If testers stall here, starting the day automatically on a new calendar day is the cut — **watch it at G2**.

**Where journey 3 breaks:**

| Break | What happens | Status |
|---|---|---|
| A one-day-old backup on a new phone looks like lost progress | The "Your progress from <date>" card | **Applied** [A] (§4, 5) |
| They come back mid-haggle after days away | The same customer, the same round. One line acknowledges it: "Still deciding?" [A] | Designed |
| **v1 content runs out:** the shop reached, all 40 items found | **No next goal.** Days still play, but the bar has nowhere to go — PRD Check 1's day 7 is exactly when this can hit | **Recommended** (§4, 4c) |
| Notifications would pull them back | v1 has none on purpose: Check 1 measures coming back by choice | Designed |

---

## 4. The answers

1. **Which screen does too much and should be two: "Lot reveal & sort".**
   - It mixed a **spectacle** (the bag emptying — the peak of the day) with an **irreversible decision** (scrap). Players tapping fast to speed up the show would land taps on Scrap.
   - **Now two:**
     - the **Reveal**: no decisions; tap to speed up; back reveals everything
     - the **Sort** list: everything Keep by default, each item's scrap value shown, one confirmation listing what goes
   - *Applied in PRD F5 and §6.*
   - *Runner-up:* the stall is busy, but it's the hub, and every element is a door to somewhere. Kept as one; revisit if testers can't find things.

2. **Which two screens are so similar they should be one: Inspect and Item detail.**
   - Both are "one item, close up": Inspect shows clues and condition; Item detail showed what you paid, the estimate and the history.
   - Two screens for one object means two places to look, and a later screen that duplicates v1's.
   - **Now one "Item" screen:** v1's Inspect, which later grows sections for price paid and history.
   - The same logic merges **Collections into the Dealer's Guide**: both track discovered items, so collections become sections.
   - *Applied in PRD §6* (later screens).

3. **Where the player is asked for something before they understand why:**
   - **a. The display slots.** Buyers only ask for what's on display, but a new player doesn't know that. With an empty display, nobody comes — and that reads as "broken", which is where people quit.
     - **Applied:** new stock fills empty slots automatically; the player can still rearrange (F6 AC8).
   - **b. The age screen (M4).** It asks for a year of birth with no reason given, and "why does a game want my birth year?" is a quit moment.
     - **Applied:** one neutral line, "Ads need an age check first.", which doesn't hint at the age that counts (CR19). ADV-1 checks the wording.
   - **c. Whether ₹ is real money.** It's not a question asked, but it's an assumption made in the first 10 seconds. In India, that matters (CR1).
     - **Applied:** the first hint says "All ₹ here is game money." (F1).
   - **Fine as is:**
     - The voice picker asks before any play, but its sample lines *are* the explanation, and it can be changed any time.
     - The loan shows its terms before accepting.

4. **Dead ends** — a screen with no forward action and no clear way back:
   - **a. Low cash, dismissed while still broke.** The player was stranded on a stall where nothing was affordable. **Fixed:** "Periyappa can help" stays on the stall while broke. *Applied in F11 and §6.*
   - **b. The newer-save block, when no update exists** (a sideloaded old build). The only button led nowhere. **Fixed:** a second button, "Write to us" (Report a problem). *Applied in §6.*
   - **c. The end of v1's content:** the shop reached, all 40 items found, so the bar has no next goal. It isn't a navigation dead end, but it's a motivation dead end on about day 7. **Recommended:** keep a standing goal after "Find all 40" — "Beat your best deal (₹X)" — and say plainly "More of the bazaar arrives in updates". Open; it ties to Q7.
   - **Checked, and not dead ends:**
     - the auction with too little cash (Skip stays)
     - Report a problem without an email app (the address is shown)
     - the privacy policy offline (back returns to Settings)

5. **Where a permission is requested, and whether the moment fits:**
   - **v1 asks for no runtime permission at all.** Vibration is granted at install, with no prompt (CR13).
   - The only system dialog v1 used to show a new player was **"Viewing full screen"**, and it came at exactly the wrong moment: over the first haggle. **Removed** (TRD TD-25).
   - **Later, one permission ever: notifications** (Android 13+).
     - **When:** its explainer comes at the **first restoration the player starts**, then Android's prompt.
     - **Why that moment is right:** the player has just started something that finishes while they're away, so "we'll tell you when it's done" makes sense.
     - **If they decline:** we never ask again, and the game works without it (DR39).
   - **Ads need no Android permission.** Our own consent comes at the first ad tap — the moment the player wants the ad (CR20).
   - **Purchases** ask nothing beyond Google Play's own sheet.

---

## 5. What this changed in the PRD

All applied on 2026-11-24 and logged in the PRD's change log. Anything here can be reverted by changing the PRD.

| # | Change | Where in the PRD |
|---|---|---|
| 1 | First launch goes from the voice picker **straight into the first haggle**. The first customer is a patient walk-in seller with a genuine item [A]. The stall is introduced after the first deal | F1, §6 |
| 2 | **Quick buttons (−10%, −25%) make an offer in one tap** [A] | F2 |
| 3 | **"Lot reveal & sort" split** into **Reveal** and **Sort**. Back in the Reveal reveals everything; Sort keeps everything by default | F5, §6 |
| 4 | **"Periyappa can help"** stays on the stall while broke | F11, §6 |
| 5 | Launch cards: **Newer save** gets "Write to us"; new **"Couldn't read your save"** and **"Your progress from <date> is here"** [A] cards | §6 |
| 6 | The **stall's evening state** ("Day N done", with the button "Start Day N+1") is named | §6 |
| 7 | **Item detail merged into Inspect**; **Collections merged into the Dealer's Guide** (later screens) | §6 |
| 8 | **Back**, on the voice picker, Reveal, Sort and kanakku | §7.7 |
| 9 | *Later:* notifications are never posted while the game is open, and a tap never interrupts a haggle or an auction | DR39 |

**Applied the same day, at the owner's request** (PRD change log):
- auto-display (§4, 3a) → F6 AC8
- the age screen's neutral reason line (§4, 3b) → CR19; ADV-1 checks the wording
- the game-money hint (§4, 3c) → F1

**Still open:** a standing goal after v1's content runs out (§4, 4c) — now part of Q7's recommendation in PRD §10.
