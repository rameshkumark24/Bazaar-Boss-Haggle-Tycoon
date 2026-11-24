# Bazaar Boss: Haggle Tycoon — the product as decided

> **Status:** agreed 2026-11-23. Legal and store changes from [RESEARCH.md](RESEARCH.md) §0 folded in the same day. USP changes from [COMPETITORS.md → USP](COMPETITORS.md#usp) folded in 2026-11-24. Your bar's numbers (§9) and the [Business verdict](#business-verdict) added 2026-11-24; your address and CA decisions recorded the same day. [Hardening decisions](#hardening-decisions) added 2026-11-24 and folded into §2, §4, §6–§9 and the verdict. This file is the source of truth. Every later step reads it, not the chat. Change the product here.
>
> **Labels:** **[F]** FACT — you said it, or you accepted my recommendation ("decided 2026-11-23") · **[A]** ASSUMPTION — inferred; correct it before it becomes the product · **[Q]** OPEN QUESTION — nobody knows yet · **[✓]** CHECKED — verified against a source (sources and tiers in [RESEARCH.md](RESEARCH.md))

## In one paragraph

A single-player, offline Android game set in Indian bazaars. You buy stock at kabadi auctions and from walk-in sellers, spot the fakes, and sell by haggling one-on-one with customers who hide their walk-away price but show their mood. Profit grows a footpath stall into a shop, a showroom and an antique gallery, city by city. Characters talk the way bazaars talk — in Tanglish or Hinglish — and the game is fair, readable and never a gamble. [F]

**Positioning (decided 2026-11-24):** for Indian phone gamers who love a good bargain but are tired of auction games that stop every few minutes for an ad, Bazaar Boss is the bazaar haggling game that **never makes you watch an ad to keep playing**, unlike Bid Wars and Bid Master. Evidence and reasoning are in [COMPETITORS.md → USP](COMPETITORS.md#usp). [F]

---

## 1. What it is

**The product** [F unless marked]
- Single-player, offline, Android (Google Play), portrait. [F; portrait decided 2026-11-23]
- **Buy:** bid live against 2–4 AI rival dealers at kabadi and godown-clearance auctions after a 10-second partial peek at the lot; customers also walk in to sell to you.
- **Sort:** open the lot — junk, repairable, valuable, rare, and some fakes; junk sells as scrap.
- **Restore:** clean and repair on timers to raise value; assistants work while you're away.
- **Sell:** haggle one-on-one. Each customer hides a reservation (walk-away) price and shows mood and patience. Haggling is the core skill.
- **Grow:** footpath stall → shop → showroom → antique gallery; unlock Coimbatore → Chennai → Madurai → Mumbai → Delhi.
- **Voices:** English, Tanglish, Hinglish. [F — decided 2026-11-23]
- **Money:** free. Only rewarded ads the player chooses to watch — no forced ads. Small non-random purchases (cosmetic themes, Dealer Pack). Never real-money stakes. [F; full-screen ads and "Remove ads" dropped 2026-11-24]
- **Engine:** libGDX (Java), no server, fully offline. [F]

**Which of the three possible products this is:** **A — haggle-first trading game.** [F — decided 2026-11-23]
- The minute-to-minute play is negotiating with characters; auctions are how you get stock; restoration and growth are the meta.
- Rejected **B (auction-first):** reads as a gamble under our rules, and needs the full rival AI before anyone can play.
- Rejected **C (idle tycoon):** haggling would shrink to a minigame and "the hard bargain won" would fade.
- From B we keep the lot reveal as the high point of a day; from C we keep offline timers as a *later* reason to return.

**Core feeling:** the thrill of a lot that turns out better than it looked, and the satisfaction of a hard bargain won. [F]

---

## 2. The complete feature set

### CORE — without these it is not the product
- Haggle rounds: price slider, quick buttons −10% / −25% / "Last price?", walk away. [F]
- Hidden reservation price, patience that drops each round, visible mood. [F]
- Every round shows what changed and why ("Annoyed — you barely moved"); never the reservation price. [A]
- Both directions: customers who buy from you and customers who sell to you. [F]
- Five personalities — tough bargainer, emotional seller, rushed buyer, collector, con artist with a fake — each punishing a different bad habit (see §3). [F; the punishing rule A]
- Items: category, base value, condition, rarity, fake chance, appraisal clues. [F]
- Inspection: clues (weight, marks, wear) to judge condition and spot fakes. [F]
- Auctions: 10-second partial peek, live bidding against rivals with budgets, risk appetite and favourite categories; rivals never cheat. [F]
- Lot opening with a satisfying reveal (a gunny bag emptied item by item — never slot-machine visuals); junk sells as scrap. [F; visuals A]
- Restoration on timers raises value, at a **fixed time and cost — never a chance of failure or of destroying the item**. [F; fixed outcomes decided 2026-11-24] (after v1)
- Reputation: bad faith (lowballing collectors, selling fakes as real) lowers future customers' trust. [F]
- Growth: stall → shop → showroom → gallery; new cities. [F]
- **A floor:** a bad bid hurts but never ends the game. [F] Scrap always sells; cheap walk-in sellers always appear; when broke, a relative ("Periyappa") offers a small interest-free loan repaid from sales. [A]
- Local save with versioned migration, from day one. [F]
- All content (items, customers, dialogue, markets, events) in JSON with a validator. [F]
- First haggle within 30 seconds of first launch; one-line hints only. [F]

### EXPECTED — players assume these exist
- Resume exactly where you left off, including mid-haggle after a phone call; save after every round. [A]
- Android back button never quits by accident. [A]
- Settings: voice (English / Tanglish / Hinglish), sound, music, haptics, text size. [A]
- Godown (inventory) with filter and sort. [A]
- Kanakku (ledger): today's deals, profit, best deal ever. [A]
- "While you were away" summary, once offline timers exist. [A]
- Progress survives reinstall or a new phone: Android Auto Backup configured + two rotating local backups. The backup carries saves and settings only; the age and ad-consent records stay on the phone. [A; H1-10]
- Credits & licences (fonts, sounds, CC BY icons). [A]
- Privacy policy link inside the app. [✓ — required by Play even when nothing is collected]
- **Report a problem** in Settings: pick a reason, read the whole message, then your email app opens. It carries the app, save and Android versions — never IDs — plus the last crash note if you choose. **Long-press any line → Report this line.** [A; H2-7, H2-9]
- **Start over** in Settings deletes everything the game keeps on the phone. [A; H2-4]
- **Nothing to type, anywhere:** no names, chat or free text — so nothing personal is stored and nothing needs moderating. [A; H3]
- Indian number grouping (1,25,000) and lakh/crore short forms (₹2.5L, ₹1.2Cr); ₹ shown with a stylised coin icon, never banknote images. [F for no banknotes; rest A]
- Mood shown as face + word, never colour alone. [A]
- Once purchases exist: restore purchases, "payment pending" state (UPI). [A]
- Once timers exist: optional notifications, with an explainer before Android's permission prompt. Scheduled on the phone (no push service), only for something the player started, at most one a day, never about ads or purchases. [A; H1-9]

### DIFFERENTIATING — why someone picks this over Bid Wars or Dealer's Life
- **Never makes you watch an ad to keep playing — the USP.** [F — decided 2026-11-24; [COMPETITORS.md → USP](COMPETITORS.md#usp)]
  - No full-screen ads, no pay-or-watch gates, no premium currency.
  - Every ad is one the player taps.
  - The store listing says **"no forced ads"**, never "no ads", because Play labels any game that shows ads "Contains ads".
- **Indian bargaining as mechanics, not decoration:** the "Last price?" ritual; walk away and get called back ("Okay okay, vaanga!" / "Achha theek hai, aao!"); "add the small lamp and I'll take it" throw-ins; bohni / போணி — the first sale of the day. [A]
- **Characters who talk like the bazaar:** code-mixed Tanglish and Hinglish banter, short and funny. [F]
- **Fair and readable:** after each deal, one line on what you left on the table ("He'd have gone to ₹380"); after each auction, every rival's estimate revealed. Answers the "rigged!" reviews auction games get. [A]
- **Appraisal you learn:** clues, a magnifier upgrade, a Dealer's Guide that fills as you discover items. [F + A]
- **Restoration you can see:** dust and rust layers peel off the item. [A] (after v1)
- **Indian seasons as gameplay:** Diwali rush, Pongal clearance, wedding-season brass and silver demand, monsoon godown damage — on an in-game calendar, not real dates. [F; calendar A]
- **Respectful by design:** offline, small download, runs on a 2–3 GB phone, no dark patterns (§3.2). [F + A]

### LATER — real, deliberately deferred
- Restoration timers, assistants, offline progress, notifications. [F, deferred]
- More cities (Chennai, Madurai, Mumbai, Delhi) with their own item pools and customer types. [F, deferred]
- Collections (radios, brass ware, clocks, toys) with set bonuses; events. [F, deferred]
- Content target 150+ items, 15+ customer types. [F, deferred]
- Native-script Tamil and Hindi UI (needs the Indic text renderer). [Q — wanted at all, now that voices are Tanglish/Hinglish?]
- **Money (Milestone 4)** [F unless marked]:
  - Rewarded ads, opt-in only: (a) extra 10-second peek, (b) finish one restoration instantly, (c) reopen a haggle after a walk-away, once per customer. At most 12 a day; the reward comes only for a completed ad; the game saves before every ad, so a failed or interrupted ad costs nothing. [A; H1-6]
  - **No interstitials** — full-screen ads the player didn't ask for. Dropped 2026-11-24 because the USP is false while they exist. [F]
  - **Age check and consent before any ad** [✓ RESEARCH L-2, L-3]:
    - A neutral year-of-birth screen comes before the ads SDK ever starts — at the first tap on an ad offer, never at launch. Only adult/minor and the date are kept, never the year; "minor" can be re-checked once a year in Settings. [A; H1-5]
    - Under 18 or age unknown → the ads SDK is never initialised. The game stays fully playable; rewarded perks become a daily in-game "Periyappa's tip".
    - 18+ → a plain notice at the first "watch an ad" tap, naming what Google collects. Buttons: *Allow & watch* / *Not now*.
    - Consent (choice, date, notice version) is saved on the phone, never backed up, and can be withdrawn in Settings → Ads & privacy; a new notice version asks again. Settings → Your data shows everything the game keeps. [A; H2-2, H2-5]
    - Tamil and Hindi versions of the notice are web pages on GitHub Pages.
  - Consent for EEA/UK (Google UMP) — only if we ever ship there. [A]
  - AdMob setup: test ads for every tester build; gambling and real-money-game categories blocked; max ad content rating "T"; app-ads.txt on GitHub Pages. [✓ RESEARCH L-6] Blocks and rating set in the AdMob console, not only in code; a player's "bad ad" report is checked in AdMob's ad review center within a week. [A; H1-7]
  - Purchases: Dealer Pack ₹149 (**cosmetic only** — a theme set and a stall nameboard style; the starter assistant is dropped [A; H1-1]), cosmetic themes ₹49; restore purchases. Prices come from Google Play at runtime, never hard-coded. ("Remove ads ₹99" dropped 2026-11-24 — with no forced ads there is nothing to remove.) [F]
  - No paid randomness, ever. **Never sell in-game cash** or anything that converts to it, and never let players trade items for money. [✓ RESEARCH L-1 — these are the regulator's money-game tests]
  - **Rewarded ads first; purchases later** [F — 2026-11-24]: **after AdMob's first payout (≈ ₹9,293), then a CA's GST answer paid from that money** [F — 2026-11-24: "let it make money first, then call CA"], then a grievance + refund page. Best case ≈ August 2027 [A — Business verdict §4]. Google may show your home address on the Play listing once purchases ship [F — 2026-11-24]. [✓ RESEARCH L-7 to L-10]
  - Google test ad unit IDs until you say "release". [F]
- Cloud save (Play Games) and achievements — only after the data table (Hardening H3) is redone, because they'd give us the player's Play Games ID [A; H1-11]. Voice lines, more languages, recurring-character story arcs. [A] ~~Content updates fetched as JSON from GitHub Pages~~ — dropped: content ships only inside app updates. [A; H1-3]
- iOS. [A — not planned]

---

## 3. The one core loop

The repeated action is **the haggle**: name a price, read the face, adjust, close or walk. A player does it 15–30 times per in-game day. [A]

```
customer arrives → offer ⇄ counter (mood, patience move) → deal or walk → verdict line → next customer
      ↑                                                                                   │
stock: auction (peek → bid → reveal) · walk-in sellers · restoration  ←  profit → upgrades
```

- **One in-game day** (the session unit, decided 2026-11-23 [F]): morning auction or walk-in sellers → 5–8 customers → evening kanakku and upgrades. About 3–5 minutes. Real time is used only for restoration and assistants, with offline time capped. [A for numbers]
- **The risk is a solved loop:** if one opening offer and one step size beat everyone, haggling is a chore by day three. Each personality punishes a different habit [A]:

  | Personality | Punishes |
  |---|---|
  | Tough bargainer | creeping up in small steps |
  | Emotional seller | insulting lowballs |
  | Rushed buyer | slow, many-round haggles |
  | Collector | lowballing them on things they love (reputation hit) |
  | Con artist | skipping inspection |

- The headless simulator plays bots with fixed habits (lowballer, creeper, splitter, impatient, honest) and must show none dominates. [F for the simulator; bots A]

### 3.2 Why players come back — retention by design

You asked for retention built on how people actually think. [F] Every mechanism below is honest. Each one gives the player something real, and none of them punishes absence or pushes a purchase. India's Dark Patterns Guidelines 2023 bind anyone offering services in India, and this list already complies. [✓ RESEARCH L-9]

| # | Mechanism | Why it works | How we use it | v1 |
|---|---|---|---|---|
| 1 | **Mastery** (competence) | People return to what they're visibly getting better at | Readable round feedback; post-deal verdict; "your best deal this week" | ✓ |
| 2 | **Goal gradient** | Effort rises as a goal gets close | Always one visible next goal with a bar ("Shop: ₹18,400 / ₹25,000"); goals nest — today, tier, city | ✓ |
| 3 | **Endowed progress** | A goal that's already started feels worth finishing | The stall-to-shop bar starts at 20% with Periyappa's gift | ✓ |
| 4 | **Open loops** (Zeigarnik effect) | Unfinished things stay on our minds | Each day ends with a teaser: "A collector wants a brass lamp tomorrow", "Kumar wants a rematch at Friday's auction" | ✓ |
| 5 | **Surprise, anchored in skill** | Variable outcomes are exciting; skill makes them fair | What's in a lot varies, but peeking and clues let a skilled player predict it. Rare visitors are a surprise. Randomness is never sold. | ✓ |
| 6 | **Peak–end rule** | Sessions are remembered by their best moment and their ending | Day-end leads with the best deal and tomorrow's teaser. A loss is shown as "Lesson: …", never as the last word. | ✓ |
| 7 | **Characters who remember you** (relatedness) | Being remembered builds attachment | 2–3 recurring customers recall your last deal ("You again! That 'brass' lamp was painted tin!") | ✓ |
| 8 | **Autonomy** | Self-chosen play is stickier | Choose whom to serve, what to bid on, honest or sharp (with reputation stakes) | ✓ |
| 9 | **Daily rhythm** (cue → routine → reward) | Habits attach to small daily cues | "Today's special" — one special lot or visitor per calendar day. **No streaks and no penalty for missed days.** | ✓ |
| 10 | **Completion** | People like to finish sets | "Found 12 of 40 items" counter in v1; collections and the Dealer's Guide later | counter |
| 11 | **Sharing a win** | Pride, plus social proof for new players | "Deal card" image (bought ₹200 → sold ₹1,800) shared via Android's share sheet to WhatsApp. Shared only when the player taps; no personal data. Carries the coin icon and "Game money — no real money" (H1-8). | ✓ (first to cut if late) |
| 12 | **Effort raises value** (IKEA effect) | We value what we fixed ourselves | Restoring items with visible wear coming off | later |
| 13 | **Fresh starts** | New chapters invite lapsed players back | New tier or city = new chapter. Returning players get "Welcome back — here's a cheap starter lot", never a guilt message. | ✓ |

**Never, by design** [A]:
- No fake urgency or countdowns on purchases.
- No guilt-trip buttons ("No thanks, I like losing money").
- No streaks that punish missed days.
- No energy systems that block play.
- No nagging for ratings or purchases (at most one in-app review prompt, after a big win).
- No ads disguised as game content.
- No forced ads: no full-screen ads, and nothing lost by skipping one. Every ad is one the player taps.
- No lot contents tuned to how much a player spends or how many ads they watch.
- No paid randomness, and no selling in-game cash.

**Retention numbers to watch:** D1 and D7 return, days played per week, haggles per day, share of days played through to the end, and share of players who return the morning after a day ends. [A]

---

## 4. Every screen

**Commonly forgotten screens, applied here**

| Screen nobody writes down | Here |
|---|---|
| Explainer before an OS permission prompt | Later, once: notifications (Android 13+), at the first restoration, never at launch. v1 asks for no permissions. [A] |
| Offline | Not a screen — the game is offline. [F] Micro-states only: "no ad right now", "can't reach Google Play". [A] |
| Force-update | No server to break; saves migrate forward. [A] |
| "This no longer exists" | No push messages or links into the app. A notification for an item that's gone opens the stall with a one-line note. [A] |
| Session expired | N/A — no accounts. [F] |
| Empty states (never-had vs filtered-to-zero) | Godown, kanakku, Dealer's Guide, collections. [A] |
| In-app account deletion | N/A — no accounts. Equivalent: Settings → Start over, which deletes the save, both backups, settings and the age and consent records on this phone. [A; H2-4] |
| Restore purchases | When purchases ship: automatic at launch + Settings button + "payment pending". [A] |
| Privacy policy / terms in-app | Privacy policy: yes, v1. [✓] Terms, refund policy and grievance page: when purchases ship. [✓ RESEARCH L-14] |
| App version in Settings | Yes, plus save-format version. [A] |
| Report a problem / report this line | v1: Settings, and a long-press on any line. [A; H2-7] |
| What the game keeps about you | v1: Settings → Share my stats. With ads: Settings → Your data. [A; H2-5] |

**All screens** (v1 column = first version, §8)

| # | Screen | What it's for | v1 |
|---|---|---|---|
| 1 | Voice picker | First launch: English / Tanglish / Hinglish, each with a sample line | ✓ |
| 2 | Stall (home) | Cash, reputation, day, display slots, next goal bar, Today's special, mute button; routes to everything | ✓ |
| 3 | Haggle | Face, mood, patience, item, slider, quick buttons, round history with reasons | ✓ |
| 4 | Deal result | Profit, verdict line, reputation change, share deal card | overlay |
| 5 | Inspect item | Clues, condition, fake suspicion | ✓ |
| 6 | Auction | 10-second peek → live bidding → result with each rival's estimate | ✓ |
| 7 | Lot reveal & sort | Gunny-bag reveal; keep or scrap | ✓ |
| 8 | Godown | All items, filter/sort, both empty states, "found X of Y" | ✓ |
| 9 | Day end (kanakku) | Best deal first, day's profit, reputation, tomorrow's teaser, upgrade | ✓ |
| 10 | Settings | Voice, sound, text size, privacy policy, credits, version, start over, share my stats, report a problem | ✓ |
| 11 | Workshop | Restoration slots, timers, fixed costs and value gained (no failure chance), optional instant-finish (ad) | |
| 12 | Assistants | Hire and assign | |
| 13 | Welcome back | What finished while you were away | |
| 14 | Upgrades | Tier, display slots, storage, magnifier, workshop slots | |
| 15 | Tier-up moment | Stall → shop celebration | overlay |
| 16 | Market map | Cities, unlock rules, travel | |
| 17 | Event card | What the current season changes, and for how long | |
| 18 | Collections | Set progress and bonuses | |
| 19 | Dealer's Guide | Items discovered, value ranges learned, clue tips | |
| 20 | Item detail | What you paid, estimate range, history, actions | |
| 21 | Reputation detail | Trust level; what raises or lowers it | |
| 22 | Help | One page on mood, patience, reputation, auctions | |
| 23 | Low cash | Loan / scrap / cheap-seller day — the never-ends floor | overlay |
| 24 | Shop (purchases) | Dealer Pack, themes, restore, pending | |
| 25 | Ad offers | Extra peek, instant finish, reopen haggle; "no ad now" state | |
| 26 | Age screen + ads consent | At the first tap on an ad offer: a neutral year-of-birth screen, then (18+) a plain notice; withdraw in Settings (RESEARCH L-2; H1-5) | |
| 27 | Notification explainer | Shown before Android's one-shot permission prompt | |
| 28 | Save recovered | "Couldn't read your save; restored yesterday's backup" | |
| 29 | Storage full | "Couldn't save — phone storage full" | |
| 30 | Credits & licences | Fonts, sounds, icons | in 10 |
| 31 | Rate prompt | Google's in-app review, after a big win only, at most once | |
| 32 | Dev: text & content test | Fonts, ₹, number formats, validator results (Milestone 0) | dev |
| 33 | Dev: haggle lab | Tune customers live (Milestone 1) | dev |
| 34 | Report a problem | A reason, the whole message to check, then the email app (H2-7) | overlay |
| 35 | Your data | Everything the game keeps on this phone; Share; Start over (H2-5) | |

---

## 5. What is harder than it looks

Legal, store and risk detail lives in [RESEARCH.md](RESEARCH.md). The product-level list:

1. **Tanglish/Hinglish has no standard spelling** ("sollunga" vs "solunga"). Keep a short style guide with spellings, tone and banned words, and have a native speaker read every line. Tanglish: you. Hinglish: a tester. [A; Hinglish reader Q]
2. **Voice is the soul of the game.** At full content that's ~4,000–5,000 lines across three voices. Write short. [A]
3. **Fair but unsolvable haggling.** Hidden price versus visible maths is resolved by showing reasons, never the price, plus the post-deal verdict and simulator bots (§3). [A]
4. **"Rigged!" reviews.** Answered by revealing rival estimates after each auction. Lot contents never depend on spending or ads. [A]
5. **Interruptions on cheap phones.** Auctions pause when the app goes to the background; save after every round; a haggle resumes after process death. [A]
6. **Saves with no server.** Write a temp file then rename it, keep two backups, configure Auto Backup, handle a full phone. [A]
7. **Clock changes skip timers.** Cap offline time, ignore negative time, don't punish anyone. [A]
8. **Notifications are unreliable on Indian phones** (OEM battery savers), so retention can't depend on them. [A]
9. **Festivals move every year.** Use an in-game calendar. [A]
10. **Content that can hurt** [A]:
    - no deity idols or temple artefacts
    - no real coins, stamps, brands, film posters or people
    - personalities never tied to region, religion, caste or community
    - women traders and customers, not only men
    - a human read-through of every line, because the validator catches words but not stereotypes
    - [✓ Play's hate-speech and IP policies — RESEARCH L-12]
11. **Code reuse is blocked.** The idle engine (`rameshkumark24/App`) isn't visible and `C:\Bus Mudhalali` is empty. v1 needs no Indic renderer (Latin script only). If the engine doesn't arrive by Milestone 0, we write the save system and number formatting fresh — roughly 1–2 days. [✓ for what was visible; Q for where the code is]

---

## 6. Decisions (made 2026-11-23)

| # | Decision | Chosen | Why it was expensive to reverse |
|---|---|---|---|
| D1 | What one session is | **Market days**; real time only for restoration/assistants, offline capped [F] | Save format, economy, simulator, tutorial, ads and events all count in days |
| D2 | Look and art source | **Drawn from shapes in code** [F] — see below | Every item, face, screen, screenshot and paid theme follows from it |
| D3 | Language pipeline | **Tanglish/Hinglish voices in Latin script; one string table** [F] — see below | Retrofitting IDs or a text renderer later is weeks of rework |

**D2 — the look in detail** [F]
- Items: a few vector parts, a material (brass, copper, wood, Bakelite, paper) and wear layers (dust, rust, cracks) that restoration peels off.
- Customers: built from a face kit (face shape, skin tone, hair, moustache, glasses, cap, bindi, saree border); mood comes from brows and mouth.
- Bazaar palette: marigold, turmeric, temple red, indigo, brass.
- Asset rule amended to: **CC0, CC BY with credit, OFL, or self-made.** game-icons.net (CC BY 3.0) is allowed for UI icons, with credits.

**D3 — the language pipeline in detail** [F]
- **Voices:** English, Tanglish and Hinglish, all in Latin script. v1 therefore needs no Tamil/Devanagari shaping.
- **String table:** a spreadsheet with columns `id | en | tanglish | hinglish | max_len | note`, exported to JSON. Content files hold IDs only.
- **Validator checks:** missing translations, broken `{placeholders}`, length budgets, and banned words in all voices — gambling vocabulary, real brands, celebrity names.
- **UI chrome** (Settings, Sound…) in plain English for every voice. Bazaar words (kanakku, godown, bohni) are shared by all voices. [A]

**Cheap now, permanent later — decided** [F]
- App ID `io.github.rameshkumark24.bazaarboss` (Play app IDs can never change)
- Portrait only
- Minimum Android 7.0 (API 24); target API 36, which Play has required for new apps since 31 Aug 2026 [✓]
- libGDX 1.14.x [✓ 16 KB-aligned natives; supports Android 16 predictive back with `enableOnBackInvokedCallback="true"` — RESEARCH R8]
- Distributed in India only at first
- Save format versioned from day one
- **Play target audience: 18 and over.** Restrict Minor Access stays off; content stays teen-suitable. [✓ RESEARCH L-3 — changed from 13+ on 2026-11-23]
- **Fonts baked to bitmaps at build time** — no FreeType at runtime, so a smaller download and one less licence credit. [RESEARCH L-11]
- **v1 ships without the internet permission**, and a build check holds every release to a permission allowlist. [A; H1-4, H2-8]
- **Play App Signing on; the upload key in a password manager plus an offline copy; keystores never in git.** [A; H2-12]

---

## 7. What success looks like — and what this is not

**Success, a month after v1 reaches real players** (any two) [A]:
1. **They come back without being pulled.** v1 has no notifications, so returning is pure choice. ≥ 7 of 14 testers are still playing on day 7 with nobody reminding them.
2. **Haggling is the reason.** Testers average ≥ 20 haggles per day played, and most can name a specific customer or deal unprompted.
3. **The pacing promise holds.** Median time to the shop tier is 30–45 minutes [F], within ±25% of the simulator's prediction.

**Go / no-go gates** — you said time is available "if it's worth it" and there's no more money without returns. [F] Gate numbers [A]:

| Gate | When | Go if | Otherwise |
|---|---|---|---|
| G1 | After Milestone 1: 5 people play the haggle screen while you watch | ≥ 3 of 5 keep playing past 10 minutes unprompted, or ask to keep playing | Rework the haggle (at most 2 rounds), then stop |
| G2 | End of the 14-day closed test | Success 1 and 2 above | Fix the top reason testers stopped, retest once, or stop |
| G3 | 30 days after public release | ≥ 1,000 installs from free channels, and D7 ≥ 10% | Rethink acquisition or the hook before building more content |

**Non-goals — what this is not** [F unless marked]
- **Not a money game:** no stakes, prizes, paid entry, paid randomness; no in-game cash for sale. [F + A]
- **Not online:** no accounts, multiplayer, chat, trading between players, or server. [F + A] Anything ever shared between players needs a server and a redesign first (Hardening H4-19).
- **Not an idle clicker:** no tapping for money; offline progress only for restoration and assistants, capped. [A]
- **Not a story game:** one-liners, no cutscenes. [A]
- **Not a real antiques guide:** invented items and values; no real brands, people, idols or coins. [F + A]
- **Not for children:** declared 18+ on Play, outside Google's Families programme. Content stays teen-suitable, and no ad ever reaches an under-18 player. [✓ RESEARCH L-3 — changed from 13+ on 2026-11-23]
- **Not a live service:** content ships in app updates. [A]
- **Not pay-to-win:** purchases never sell what normal play earns in reasonable time. [A]
- **Not ad-gated:** no forced ads, no pay-or-watch-or-lose gates, no premium currency, no chance-based repairs. [F — decided 2026-11-24]

---

## 8. The first version — smallest worth shipping

**Goal:** one real player completes buy → haggle → profit → grow, and you learn whether people want more haggles. This build goes into the 14-tester closed test. [F testers] If G2 passes, it goes to production in India.

**In**
- Market days. First haggle within 30 seconds. [F]
- The full Milestone 1 haggle engine, in both directions. [F]
- 5 personalities across 8 customer types. [F]
- 40 items in about 6 categories, each with condition, fakes and 2–3 clues. [F; category count A]
- Kabadi auction lite: one per day, 2 rivals, 10-second peek, live bidding, gunny-bag reveal, scrap. [A]
- One reputation meter. [F]
- Coimbatore only. [F]
- Stall → shop (30–45 minutes), with the showroom visible as the next goal. [F + A]
- The low-cash floor. [A]
- Voices: English, Tanglish, Hinglish. [F] Hinglish needs a native read-through before the closed test. [Q who]
- Retention hooks from §3.2 marked ✓: next-goal bar, tomorrow's teaser, Today's special, 2–3 recurring characters, found-counter, deal card. [A]
- Save/resume, two backups, Auto Backup, back button, text size. [A]
- **Privacy and safety from the first tester** [A; Hardening]: no internet permission (H1-4); Settings → Start over, Share my stats, Report a problem, privacy policy, credits & licences; long-press Report this line; the crash note (H2-9); backup of saves and settings only (H1-10); the deal card marked "Game money" (H1-8); the build guard (H2-8).
- **Sound:** basic sound effects (CC0 / jsfxr), plus a mute button on the stall screen and in Settings, available from the very first screen. [F — decided 2026-11-24; COMPETITORS §7]
- Under the hood [F, trimmed]: JSON content with the validator, string table, unit tests for haggle and auction maths and for save/load migration, and a desktop headless haggle simulator.

**Out — waits for G2:**
- Restoration, assistants, offline income, notifications.
- Other cities, events, collections, Dealer's Guide.
- Magnifier and other upgrades.
- Rival bluffing, godown auctions.
- Ads, purchases, consent.
- Themes, achievements, cloud save, native-script UI.

**Screens: 9** — voice picker, stall, haggle, inspect, auction, lot reveal, godown, day end, settings — plus overlays for the deal result, tier-up, low cash, hints and Report a problem. Of the commonly forgotten screens, only the **privacy policy link** is store-required for v1. [✓]

**Build order** (each milestone ends with: what to test on your phone, known issues, wait for "go") [F for the gating; order A]

| Step | Contents |
|---|---|
| M0 | New libGDX 1.14.x project; content schemas (item, customer, market, event); validator; string table; save system; Indian number formatting; dev text/content screen |
| M1 | Haggle engine + haggle lab + unit tests + simulator bots → **G1** |
| M2-lite | Auction lite, lot reveal, inspection, scrap |
| M3-lite | Day loop, stall → shop → showroom goal, godown, kanakku, settings, retention hooks, low-cash floor |
| M5-lite | Safety basics (Hardening H2-4, H2-7 to H2-9); privacy policy on GitHub Pages, written from the data table (RESEARCH L-14; Hardening H3), credits & licences, store listing (RESEARCH §3) promising "no forced ads", never "no ads", Data safety ("no data collected"), ad ID declaration "no", target audience 18+, content rating, closed test with ≥ 12 of 14 testers opted in for 14 days → **G2** |
| After G2 | Public release in India. Then Milestone 4, 3 slices: age screen and consent at the first ad tap; rewarded placements with the 12-a-day cap and save-before-ad; AdMob console controls; Your data; privacy policy v2 with Tamil/Hindi notice pages; app-ads.txt; Data safety update. No interstitials. Rules: RESEARCH.md and Hardening H4 → **G3** |
| After G3 | Restoration, assistants, offline income; more cities; events; collections. **Purchases** (3 slices, cosmetic only) once AdMob has paid out and a CA has answered on GST [F — 2026-11-24]; best case ≈ August 2027 [A] |

---

## 9. Your bar

| Question | Answer |
|---|---|
| **What you want back** | Money and users. [F] **₹10,000 a month, 12 months from 24 Nov 2026 (i.e. November 2027)**, after Google's share and before income tax. [F — 2026-11-24] Users come first, because ad money only follows retained users. [A] |
| **What you can put in** | Time — yes, if it proves worth it. [F] **About 5 hours a week** [F — 2026-11-24]; for how many weeks [Q]. Money: nothing beyond the Play account you already have. [F] |
| **What you already have** | A Google Play developer account — **personal, created after 13 Nov 2023, so the 12-tester / 14-day closed test applies.** [F — 2026-11-24] 14 testers. [F] A private GitHub repo for this project and a GitHub Pages site (rameshkumark24.github.io) to host the privacy policy. [✓] **Your own social account** (platform and follower count [Q]) **and the gaming/local communities you post in.** [F — 2026-11-24] Skills, market knowledge and a 2–3 GB test phone: [Q]. |
| **Where you sell first** | Google Play [F], India only at first [F — decided 2026-11-23]. Prices in ₹. |
| **What it may cost** | Free tools and free tiers only. [F] No more investment without returns. [F] A CA only once the game has made money, paid from it. [F — 2026-11-24] Play account already paid. [F] Apple: not planned. [A] |

## Open questions (consolidated)

1. Where are the idle engine and the Bus Mudhalali text code — and are they still wanted, given Latin-script voices?
2. ~~Hours per week~~ — answered 2026-11-24: about 5. Still open: for how many weeks.
3. ~~Play account type~~ — answered 2026-11-24: personal, created after 13 Nov 2023.
4. Who reads the Hinglish lines as a native speaker?
5. ~~Address on Play~~ — answered 2026-11-24: yes, Google may show your home address once purchases ship. Still open before the first sale: the CA's GST answer (RESEARCH ADV-2) — after AdMob's first payout, by your rule (2026-11-24) — and whether the grievance page must publish a phone number (ADV-3).
6. Is native-script Tamil/Hindi UI still wanted later?
7. Can you draw (for art direction), and do you have a 2–3 GB RAM phone to test on?
8. Which platform is your social account on, and roughly how many followers? (Moves only one row of the reach estimate — Business verdict §2.)

---

## Business verdict

> **Written 2026-11-24; updated the same day for your address and CA decisions and for the hardening pass, which adds a slice to the first version.** Judged against your bar (IDEA §9, numbers given today) and the first version (IDEA §8) — not the whole of this file. Inside this section, §0–§8 mean its own parts; the rest of this file is cited as "IDEA §n".
>
> **Every number is labelled:**
> - **SEEN** — a price, fee or rate opened today; `[B#]` links are in the table at the end of this section, and `[C#]` / `[S#]` are in [COMPETITORS.md](COMPETITORS.md) / [RESEARCH.md](RESEARCH.md)
> - **DERIVED** — worked out from seen numbers, with the arithmetic shown
> - **ASSUMED** — a guess, with what would confirm it
>
> Rupees use **₹92.93 per US$** (SEEN [B1], 23 Nov 2026). "Month 12" means **November 2027**.

**In one line: TEST FIRST — the verdict rests on ASSUMED numbers.** ₹10,000 a month needs about 10,350 installs a month from free channels, which is RARE; my likely estimate is about 400. A one-week waitlist test settles it before any code is written: go at 1,600 sign-ups, stop below 370.

### 0. The bar and the first version

**Your bar** (IDEA §9) [F unless marked]:
- **Want back:** ₹10,000 a month in November 2027, after Google's share, before income tax.
- **Put in:** about 5 hours a week; no money beyond the Play account. Your hours carry no price, because the bar gives none.
- **Already have:** a personal Play account created after 13 Nov 2023 (so the closed test applies), 14 testers, GitHub Pages, your own social account, and the gaming/local communities you post in.
- **Sell first:** Google Play, India, in ₹.

**The first version** is IDEA §8 as written: 9 screens plus overlays, in which one player completes buy → haggle → profit → grow. It earns **₹0 by design** — no ads or purchases until G2. Money needs two more steps, costed below:
- Milestone 4: rewarded ads.
- Purchases: after AdMob's first payout and a CA's GST answer paid from it (your rule, 2026-11-24); best case ≈ August 2027 (§4). The address is settled: Google may show it (open question 5). Also needed: the grievance + refund page (IDEA §2 LATER).

### 1. Who pays

| Who pays | For what | What they pay today | What we'd take |
|---|---|---|---|
| Nobody, in v1 | — | — | ₹0 — v1 has no ads or purchases (IDEA §8) [F] |
| **Advertisers**, through AdMob. The player pays only with ~30 seconds of attention, and only when they tap | One rewarded view | India, rewarded: **$0.63** per 1,000 Android views (Appodeal, Sept 2024) SEEN [B4] to **$2.43** (Lumikai, 2025) SEEN [B5] = **₹59–₹226** per 1,000 DERIVED (× 92.93). Falling: Android rewarded in India **−5.18%** from Q1 to Q2 2026 SEEN [B6]. The same view pays **$13.00** in the US SEEN [B4] | The market rate — you can't set it |
| **Players**, once purchases ship (after ad money and a CA) | Dealer Pack, cosmetic themes | Genre price lists: ₹5–₹11,800 per item (Bid Wars 2, Bid Master), ₹75–₹720 (Pawn Shop Master), ₹249 / ₹499 up front (Dealer's Life) — SEEN [C1 C3 C4 C5 C6]. Indian casual-game payers spend **$3 ≈ ₹279** each (Lumikai, 2025; the period isn't stated) SEEN [B5] / DERIVED | **₹149** and **₹49** [F, IDEA §2]. After Google's **15%** SEEN [B2], you get **₹126.65** and **₹41.65** DERIVED |

- **Would they accept ₹149 / ₹49?** ASSUMED yes. Both sit at the bottom of the genre's price lists and under the ₹279 a casual payer spends. Confirm with the purchase rate in Play Console after launch.
- **Google's fee.** India isn't named in Google's 2026 fee changes. It stays in "all remaining markets": 15% on the first $1M a year for developers enrolled in the 15% tier SEEN [B2]. The new structure reaches "Rest of World" on 30 Sep 2027 SEEN [B3]; whether that includes India isn't stated.

### 2. The reachable market — year one, built up from what you have

Live from about 22 Mar 2027 to the end of November 2027: **8.1 months** DERIVED (§3).

| Channel (what you have) | How | Worst | Likely | Best |
|---|---|---|---|---|
| 14 testers | Launch-day installs, plus friends they tell | 20 | 40 | 60 |
| Communities you post in | Launch posts: number × views × share who install | 3 × 1,000 × 1% = 30 | 4 × 3,000 × 2% = 240 | 5 × 10,000 × 3% = 1,500 |
| Communities, later | Follow-up posts, per month | 10 | 40 | 200 |
| Your social account | 4 short clips a month × views × share who install | 4 × 300 × 0.2% = 2 | 4 × 1,500 × 0.3% = 18 | 4 × 20,000 × 0.5% = 400 |
| Play Store search and browse | Per month | 2 a day = 61 | 10 a day = 304 | 60 a day = 1,824 |
| An Indian creator covers it, unpaid | Once | 0 | 0 | 3,000 |
| Word of mouth (deal cards) | Multiplies everything: 1 ÷ (1 − k), k = new players each player brings | ×1.05 | ×1.11 | ×1.33 |
| **Installs a month by Nov 2027** | (later posts + clips + Play) × word of mouth | **77** | **402** | **3,232** |
| **Installs in year one** | launch burst + 8.1 months of the line above | **≈680** | **≈3,600** | **≈32,300** |

- **Every channel number is ASSUMED.**
  - The test in §7 checks your own channels.
  - Play Console's acquisition report for the first 30 days after launch (G3) checks store traffic.
  - Your follower count is still open (question 8): it moves only the clip row, because short-video reach mostly comes from the platform, not from followers [ASSUMED].
- **People who see it, likely:** about **76,000 views** of your posts and clips in year one. DERIVED: 4 × 3,000 at launch + 8.1 months × 2,000 follow-up views + 32 clips × 1,500.
- **Your own gate agrees.** G3 asks for 1,000 installs in the first 30 days. The likely launch month gives about **710** (311 + 402) DERIVED.
- **Left out:**
  - **Paid installs** — they lose money (§3).
  - **Play featuring and Play Pass** — Google chooses those, so they can't be planned [ASSUMED].

### 3. The cost of the first version

**To build: 17 working slices** (two sessions each, one to build and one to review):

| Step | Slices |
|---|---|
| M0 | 1 project, fonts, ₹ formatting · 2 content schemas, validator, string table · 3 saves and backups |
| M1 → **G1** | 4 haggle engine + tests · 5 personalities + simulator bots · 6 face kit + haggle screen + haggle lab |
| M2-lite | 7 items, inspection, the 40 items · 8 auction with 2 rivals · 9 gunny-bag reveal, scrap, godown |
| M3-lite | 10 day loop, stall, goal bar, tier-up, low-cash floor · 11 kanakku, retention hooks, deal card · 12 voice picker, settings, sound + mute, back button, resume |
| Content and tuning | 13 dialogue in 3 voices (you check the Tanglish) · 14 economy tuning, cheap-phone check |
| Safety basics (Hardening H2) | 15 Report a problem, Report this line, crash note; Start over's full wipe; permission and backup checks |
| M5-lite → **G2** | 16 release build, privacy policy from the data table, listing, Play Console forms · 17 fixes during the closed test |

- **Your time:** 17 slices × 2 sessions × **2 h a session** (ASSUMED — your sessions set this) = **68 h** = **13.6 weeks** at 5 h a week. DERIVED.
  - Each extra hour per session adds 6.8 weeks.
- **Waits nobody can shorten:**
  - The closed test: ≥ 12 testers opted in for **14 days** in a row SEEN [B14]. Slice 17 runs inside it.
  - Production-access review: "usually takes seven days or less" SEEN [B14].
- **Total: 16 weeks to the first public player** = 12.8 weeks for slices 1–16 + 2 weeks of test + up to 1 week of review. DERIVED. Hardening's slice 15 adds the extra week.
  - Starting 2 Dec 2026, after the test week: G1 ≈ 5 Jan 2027, closed test ≈ 1–15 Mar, public ≈ 22 Mar 2027.
  - Holidays aren't counted.
- **To earn the first rupee: +3 slices** (age screen and consent; rewarded placements, cap and AdMob setup; Your data, privacy policy v2 and notice pages — Hardening H2) ≈ 2.4 weeks, plus AdMob's app review, "typically 2-3 days" SEEN [B17]. Ads live ≈ 10 April 2027.
- **Purchases: +3 more slices** ≈ 2.4 weeks (billing; shop and cosmetic catalogue; terms, refund and grievance pages). They wait for AdMob's first payout and a CA's answer paid from it (your rule, 2026-11-24): best case ≈ August 2027 (§4). The address is settled (2026-11-24).

**Cash before the first user**

| Item | ₹ | Why |
|---|---|---|
| Play developer account | 0 | Already paid ($25 ≈ ₹2,323 DERIVED) [F] |
| Privacy policy hosting | 0 | Your GitHub Pages site [✓ IDEA §9] |
| Engine, tools, fonts, icons, sounds | 0 | libGDX (Apache-2.0), Noto (OFL), game-icons (CC BY), jsfxr — RESEARCH L-11 |
| 14 closed testers | 0 | You have them [F] |
| A 2–3 GB test phone | 0 | Not needed to launch. Borrow one if you have none (question 7) |
| Your Claude plan | not counted | Something you already have. If you'd cancel it without this project, add its monthly price here |
| **Lawyer** | 0 — not needed | v1 collects no personal data, shows no ads and sells nothing. Every ADVICE item in RESEARCH (ADV-1 to 5) is triggered later — ads after May 2027 [S2], purchases, or a regulator notice |
| **Online-gaming licence or registration** | 0 — not needed | Social games register only if the Government notifies their category, and it hasn't [S7 S8] |
| **GST registration** | 0 — not needed | Required only above **₹20 lakh** a year: CGST Act s.22(1), "exceeds twenty lakh rupees" SEEN [B18]. Your whole bar is ₹1.2 lakh a year DERIVED |
| **Total** | **₹0** | |

**Later, if it grows** — none of these is needed to launch or run v1:
- A CA, before purchases (ADV-2) — paid from the first AdMob payout, by your rule (2026-11-24). The e-commerce check (ADV-3) comes at the same point.
- A privacy-law check of the ads consent, by 13 May 2027 if ads are live (ADV-1). The consent design is already built in; the check is prudence, paid from revenue.
- ~~A business address~~ — not needed: you'll show your home address (2026-11-24). A second phone number only if the grievance page must publish one and you'd rather not use yours (ADV-3).
- A cheap test phone.

**To run each month**

| Item | ₹ a month |
|---|---|
| Hosting, server, APIs | 0 — fully offline, no server [F]; GitHub Pages [✓] |
| Store's cut | 15% of purchases SEEN [B2]. Nothing extra on ads: the eCPMs above are what publishers receive [ASSUMED — confirm in your AdMob reports] |
| Payment fees | 0 known. AdMob pays only once your balance reaches **$100 ≈ ₹9,293**: SEEN [B15] for the USD row (the table has no INR row) / DERIVED. Earnings below it roll over to the next month SEEN [B16] |
| **Fixed total** | **₹0** — plus 15% of whatever purchases bring |

**To win one paying user**
- **₹0 in cash** through your own channels. The time isn't priced, per your bar.
- **Buying installs never pays back:**
  - India, Android, casual games: **$0.12 ≈ ₹11.15** per install SEEN [B11] / DERIVED. The low end is $0.08 ≈ ₹7.43 SEEN [B12] / DERIVED.
  - One buyer costs ₹11.15 ÷ 0.5% = **₹2,230** and pays you ₹153. DERIVED
  - Even the best case earns ₹4.25 per install — below the cheapest install. DERIVED

### 4. ⭐ The ROI math

**What one install earns over its whole life**

| | Worst | Likely | Best | Label |
|---|---|---|---|---|
| Days a player plays, counting the install day | 2.31 | 2.92 | 5.32 | DERIVED — below |
| Players past the 18+ age screen | 70% | 75% | 80% | ASSUMED — your age-screen counts |
| …who watch ≥ 1 ad that day | 25% | 40% | 50% | ASSUMED — no neutral benchmark exists; Playio's worked examples use 25/40/50% SEEN [B7] |
| Views per watcher a day (peek + reopen haggle) | 1.5 | 2 | 3 | ASSUMED — your AdMob reports |
| **Views per player-day** | 0.26 | 0.60 | 1.20 | DERIVED — the three rows multiplied |
| India rewarded eCPM | $0.63 | $1.24 | $2.43 | SEEN [B4] / DERIVED √(0.63 × 2.43) / SEEN [B5] |
| **Ad income per player-day** | ₹0.015 | ₹0.069 | ₹0.271 | DERIVED — views × eCPM ÷ 1,000 × 92.93 |
| **Ad income per install** | ₹0.04 | ₹0.20 | ₹1.44 | DERIVED — × days played |
| Installs who ever buy | 0.2% | 0.5% | 1.5% | ASSUMED — below |
| Spend per buyer, after Google's 15% | ₹126.65 | ₹153 | ₹187 | DERIVED from ASSUMED ₹149 / ₹180 / ₹220 |
| **Purchase income per install** | ₹0.25 | ₹0.77 | ₹2.81 | DERIVED |
| **Income per install** | **₹0.29** | **₹0.97** | **₹4.25** | DERIVED |

- **Days a player plays** is the sum of daily return rates over 180 days, interpolated through GameAnalytics' 2026 mobile points SEEN [B8]:
  - Worst = the median game: D7 "just under 4%", D30 0.69–0.79%, D1 ≈ 22% (D1 ASSUMED — secondary summaries; the post gives no D1 median).
  - Likely = midway to the top 25% (D1 30%, D7 6–7%, D30 1.6–1.8%).
  - Best = the top 10% (D1 40%, D7 11–12%; D30 3.0% DERIVED from the top-25% D7→D30 ratio).
- **Share who buy:** F2P games average **2–5% of players paying over their lifetime** (Business of Apps and Udonis, 2025) SEEN [B9].
  - Ours is set lower: two cosmetic items, and nothing that unblocks play.
  - There's "no published benchmark worth quoting" by genre and region SEEN [B10].
  - Confirm with Play Console after purchases ship.

**Against the bar**

| | Worst | Likely | Best |
|---|---|---|---|
| Paying users needed to cover the running cost (₹0) | 0 | 0 | 0 |
| **Installs a month to reach ₹10,000** — ads + purchases (₹10,000 ÷ income per install) | 34,600 | **10,350** | 2,355 |
| …of whom buy, per month | 69 | 52 | 35 |
| Installs a month to reach ₹10,000 — **rewarded ads only** | 281,000 | 49,700 | 6,940 |
| Installs a month you can reach by Nov 2027 (§2) | 77 | 402 | 3,232 |
| Players on a typical day in month 12 (installs × days played ÷ 30.4) | 6 | 39 | 565 |
| **Income in month 12** — ads + purchases (installs × income per install) | **₹22** | **₹389** | **₹13,700** |
| Income in month 12 — ads only | ₹3 | ₹81 | ₹4,700 |
| Months to break even | 0 | 0 | 0 |
| **Months to the bar** | never | never at this reach — 26× short | ≈ month 10 (Sept 2027): purchases wait for the first ad payout (≈ 21 July) and a CA |

All DERIVED from the table above and §2.
- **Break-even on cash is month 0, because you put in ₹0.**
  - The $25 Play fee you paid earlier (₹2,323) takes about 6 months of the likely income to come back (₹2,323 ÷ ₹389). DERIVED
- **In the likely case, ad money never reaches your bank.**
  - Ads alone earn ₹81 a month, so AdMob's ~₹9,293 threshold takes about **115 months**. DERIVED
  - In the best case, the first ad payout comes about 3 months after ads go live: ≈ ₹4,650 a month from ≈ 10 April passes ₹9,293 in June, and AdMob pays on ≈ 21 July. DERIVED. Under your rule, that payout pays the CA and unlocks purchases.
- **More ads can't rescue it.** If every player watched 2 ads every day they played, the likely case would still earn only ₹0.67 per install, and ₹10,000 would still need ~15,000 installs a month. DERIVED
  - The ads route is capped by India's eCPM, not by engagement.

### 5. The one number that decides it

**Installs a month from free channels, by November 2027** — ASSUMED, with my likely estimate at **402** (§2).
- **What the likely case needs:** **10,350 a month** (₹10,000 ÷ ₹0.97) — 26× my estimate. On rewarded ads alone it needs 49,700.
- **This is RARE.**
  - Appfigures counted every app and game on both stores with more than 100 downloads in a month: **81% got under 10,000** that month (47.7% under 1,000; 33.3% between 1,000 and 10,000) SEEN [B13, July 2023].
    - So 10,350 a month is the top fifth of apps that get any real downloads — a band crowded with apps that buy their users. Bid Master adds ~201,000 a week [C13, AppGoblin estimate].
  - Pawn Shop Master has 10M+ downloads behind it and gets about **1,330 a month** now [C14, AppGoblin estimate; DERIVED 308 a week × 52 ÷ 12].
  - Dealer's Life 2 has averaged about **14,700 a month worldwide** since Feb 2022, with Play Pass and a Steam following behind it [C15, AppGoblin estimate; DERIVED 837,000 ÷ 57 months].
  - The bar therefore asks a solo, India-only, zero-budget game for about 70% of Dealer's Life 2's worldwide average.
- **Runner-up — whether purchases exist at all.** Without them, even every lever at its best reaches ₹4,700. The address no longer stands in the way (2026-11-24). Purchases now wait for AdMob's first payout and a CA (your rule). In the likely case ads take years to pay out (§4), so the likely case stays ads-only.

### 6. What kills it

| # | Why it fails | From | Fatal on its own? |
|---|---|---|---|
| 1 | **Nobody finds it.** Free channels bring hundreds of installs a month, not the ~10,350 the bar needs, and bought installs cost more than they earn (₹11.15 vs ₹0.97) | RESEARCH R1 (score 25); §2–§3 | **No.** Every game without a budget lives with it. It caps the money, not the game |
| 2 | **India pays little per rewarded view.** $0.63–$2.43 per 1,000 [B4 B5], and falling [B6]. With ads only when players tap, a player earns ~₹0.20 from ads over their whole stay (likely). This replaces RESEARCH's unverified $0.70–4 | RESEARCH R3 (16) | **No** — purchases carry ~80% of likely income (₹0.77 of ₹0.97). **But fatal to an ads-only plan** against this bar: the best case reaches ₹4,700 |
| 3 | **The haggle isn't fun, gets solved, or runs dry**, so players leave within days | RESEARCH R6 (15), R4 (16), R12 (12); COMPETITORS §3 theme 2 (27 of 99 reviewers) | **No.** G1 tests it after ~5 weeks, before most of the cost |

No door is closed by law or practice. Every STOPS IT item in RESEARCH §1 is avoided by design.

### 7. The cheapest test — one week, ₹0, about 4½ hours

**What to do:**
1. **The page (~2 h of your time):** in one session I build a one-page site on your GitHub Pages. It has:
   - the promise: free; never makes you watch an ad to keep playing
   - three sample lines, in English, Tanglish and Hinglish
   - one mock haggle picture
   - a waitlist, using a free Google Form: email, plus which voice they want. One line on the page says the list is only for the launch message, and you'll delete anyone's entry on request.
2. **The clip (~1 h):** you record a 20–30-second clip on your phone — your voice doing a Tanglish haggle over the mock.
3. **Post it (~1 h):** on your own account, plus one post in each of 3 communities you're in, within their self-promotion rules.
4. **Day 7 (~½ h):** count the sign-ups and each post's views.

**What it measures:** how many people your own channels reach in a week, and how many of them act — the part of §5 you control.

**The result that means go** — DERIVED from ASSUMED rates:
- A likely week gives about **63 sign-ups**: (1 clip × 1,500 + 3 posts × 3,000 views) × 2% visit the page × 30% join.
- **Go at ≥ 1,600 sign-ups.** The bar needs 25.7× my likely reach at ordinary economics (10,350 ÷ 402), and 63 × 25.7 = 1,621.
- **Stop below 370.** Even best-case economics need 5.86× (2,355 ÷ 402), and 63 × 5.86 = 369. Below that, ₹10,000 by November 2027 is out of reach on free channels.
- **Between 370 and 1,599:** build only to G1 (~5 weeks), then re-run this verdict with real tester retention.

**Its weakness:**
- A waitlist measures your channels, not Play Store traffic. The thresholds assume store traffic grows with the attention you create.
- Store traffic can only be measured after launch (G3).
- **My likely estimate predicts a stop.** That's why it's worth one week now, rather than 16 weeks first.

**Not the first version:** the test costs one week; the first version costs 16. So the test comes first.

**The page is built** (2026-11-24): [site/bazaar-boss/index.html](../site/bazaar-boss/index.html), with form setup, publishing and phone-check steps in [site/README.md](../site/README.md).

### 8. ⭐ The verdict

**TEST FIRST — rests on ASSUMED numbers (reach, share who buy, ad engagement).** ₹10,000 a month in November 2027 needs ~10,350 installs a month from free channels — RARE, against a likely 402 — so run the one-week waitlist test (§7) before the build. Go at ≥ 1,600 sign-ups; stop below 370.

**Why** — your rules, applied:
- **Not DROP IT.**
  - Nothing is fatal (§6).
  - The best case reaches the bar — **₹13,700** in month 12, from about September 2027 — *with purchases*, which your rule lets in after the first ad payout (≈ July).
- **Not DO IT.**
  - The likely case makes **₹389** a month, 26× short.
  - The bar needs a RARE number (§5).
- **What you put in isn't the problem.** It fits: ₹0 cash, and 16 weeks at 5 h a week. The gap is money per Indian player times the reach of free channels — and the build doesn't change either one.
- **The decision that could have flipped it to DROP IT is made.**
  - Without purchases the plan is rewarded ads only, and even the best case reaches **₹4,700** in month 12, which misses the bar.
  - On 2026-11-24 you agreed to let Google show your address (open question 5), so purchases stay in the plan and the verdict stays TEST FIRST.
  - Still needed before the first sale: AdMob's first payout, then a CA's GST answer paid from it (your rule, 2026-11-24), and the grievance + refund page (L-9).

**What would have to be true to change the verdict:**
- **To DO IT:**
  - your channels bring ~26× my likely reach (the test says ≥ 1,600)
  - purchases ship by about August 2027 — under your rule, that needs AdMob's first payout by July
  - retention and the share who buy land at ordinary levels
- **To DROP IT:**
  - the test comes in below 370, or
  - purchases are ruled out after all. The address no longer can do that; the CA's GST answer could (filing work you won't take on), and so could ad money that never reaches AdMob's payout.

**Would a narrower version, or another form, pass?**

| Form | Passes? | Why |
|---|---|---|
| Narrower: haggle-only, no auctions (~10 slices) | No | Players arrive ~5 weeks sooner, but income per player and reach don't change. G1 is already the narrow test of the fun |
| A web page (playable haggle in the browser) | No, alone | No install step, and easy to share on WhatsApp, so it may reach more people per post [ASSUMED]. Web ads in India don't pay more per player [ASSUMED]. A good test vehicle if the waitlist lands in the middle band |
| Different payer — paid up front, India, ₹99 | Unlikely | Needs ~119 sales a month (₹10,000 ÷ ₹84.15) DERIVED. India-only paid games sell few [ASSUMED]. It drops "free" from the USP |
| Different payer — paid up front, worldwide, $2.99 | Unproven | The smallest count of all: ~42 sales a month (₹10,000 ÷ ₹236) DERIVED. But it's a different product, head-on against Dealer's Life (₹249–₹499, 35 item packs [C5 C6]) — the one corner COMPETITORS calls already served |
| Different payer — Play Pass | Can't plan | Google picks the games and doesn't publish payouts [ASSUMED] |
| Different payer — a brand sponsor | No | That's work-for-hire, a different business. Sponsored content also clashes with "no ads disguised as game content" (IDEA §3.2) |

**If the test says go — DO IT, with this stop rule:**
- **G1 ≈ 5 Jan 2027, G2 ≈ 15 Mar 2027, G3 ≈ 21 Apr 2027**, as set in IDEA §7.
  - G3 is ≥ 1,000 installs in the first 30 days and D7 ≥ 10%.
- **Money: ≥ 4,200 installs in June 2027** — on a straight line to 10,350 a month in November (10,350 × 3.3 ÷ 8.1) DERIVED.
  - **Below that, stop building content** and leave the game up as it is.
  - **Then check AdMob's balance.** If installs pass but the balance won't reach ₹9,293 by the end of July, your "money first" rule holds purchases past November. That's the moment to decide whether to pay a CA from your pocket — the only way purchases still make the bar.

### Sources for this section (all opened 2026-11-24)

| ID | Source | What it gave |
|---|---|---|
| B1 | [x-rates — USD to INR](https://www.x-rates.com/calculator/?from=USD&to=INR&amount=1) | ₹92.925689 per $1, 23 Nov 2026 20:47 UTC |
| B2 | [Play Console Help — Service fees](https://support.google.com/googleplay/android-developer/answer/112622?hl=en) | "All remaining markets": 15% on the first $1M a year for developers enrolled in the 15% tier |
| B3 | [Play Console Help — 2026 fee changes](https://support.google.com/googleplay/android-developer/answer/16954621?hl=en) | Announced 4 Mar 2026; "Rest of World" 30 Sep 2027; India not named |
| B4 | [Moonetix — Highest CPM countries](https://moonetix.com/academy/monetization/highest-cpm-countries) (relays Appodeal's public benchmarks, Sept 2024) | India Android rewarded $0.63; US $13.00 |
| B5 | [Outlook Respawn — Lumikai: India's gaming market $1.5B in CY25](https://respawn.outlookindia.com/gaming/gaming-news/lumikai-india-gaming-market-1-5-billion-rmg-ban-2025) (relays Lumikai, 23 Mar 2026) | India rewarded eCPM $2.43; casual ARPPU $3 |
| B6 | [Bidlogic — Q2 2026 eCPM trends](https://bidlogic.io/2026/07/31/q2-2026-ecpm-growth-interstitial-rewarded-video-and-banner-trends/) | India Android rewarded −5.18%, Q1 → Q2 2026 |
| B7 | [Playio — Mobile game ad revenue calculator](https://blog.playio.co/mobile-game-ad-revenue-calculator) (16 Oct 2026) | No neutral benchmark for views per player or share who watch; worked examples use 25/40/50% and 2–5 views |
| B8 | [GameDev Reports — GameAnalytics 2026 benchmarks](https://gamedevreports.substack.com/p/gameanalytics-mobile-and-pc-game) (4 Jun 2026; 16,262 mobile games) | Mobile D1/D7/D30 by percentile |
| B9 | [Omegapoint — IAP payer conversion](https://omegapoint.systems/benchmarks/iap-conversion-payer) (relays Business of Apps and Udonis, 2025) | 2–5% of F2P players pay over their lifetime |
| B10 | [Playio — ARPPU in mobile games](https://blog.playio.co/arppu-mobile-games) (26 Oct 2026) | "neither payer conversion nor ARPPU has a published benchmark worth quoting" |
| B11 | [Linkrunner — CPI benchmarks](https://linkrunner.io/tools/cost-per-install-benchmark) (undated) | India Android casual gaming $0.12 per install |
| B12 | [Playio — Mobile game CPI benchmarks 2026](https://blog.playio.co/mobile-game-cpi-benchmarks-2026) (2 Sep 2026) | India casual Android $0.08–$0.30 |
| B13 | [Appfigures — Analyzing app downloads](https://appfigures.com/resources/insights/20230714) (14 Jul 2023) | Of apps and games with >100 downloads a month: 47.7% got 100–1K, 33.3% 1K–10K |
| B14 | [Play Console Help — Testing requirements for new personal accounts](https://support.google.com/googleplay/android-developer/answer/14151465?hl=en) | 12 testers for 14 days; review "usually takes seven days or less" |
| B15 | [AdMob Help — Payment thresholds](https://support.google.com/admob/answer/2772208?hl=en) | USD: $100 payment threshold; no INR row |
| B16 | [AdMob Help — Payment timeline](https://support.google.com/admob/answer/2772140?hl=en) | Balance checked on the 20th; below the threshold, it rolls over |
| B17 | [AdMob Help — App readiness](https://support.google.com/admob/answer/10564477?hl=en) | App reviews "typically take 2-3 days" |
| B18 | [CGST Act 2017 s.22 (text)](https://www.knowyourgst.com/gstlaw/cgst-act/22-persons-liable-for-registration-22/) | Registration if turnover "exceeds twenty lakh rupees" |

SCORE — first version: 16 weeks · likely at month 12: ₹389 a month · needs: ~10,350 installs a month from free channels (likely 402 — RARE) · TEST FIRST

---

## Hardening decisions

> **Written 2026-11-24.** Read together with [RESEARCH.md](RESEARCH.md) (risks R#, law L-#, sources S#) and [COMPETITORS.md](COMPETITORS.md). This section **changes the design**. The feature list (§2), screens (§4), cheap-now decisions (§6), non-goals (§7), the first version and build order (§8), and the Business verdict's build plan are edited in place. Each edit points back here by ID (H1-1, H2-7 …). Every change is **[A]** until you accept or change it.

**In short:**
- **The design was already safe by construction:** offline, no accounts, nothing typed in, no money in v1. Hardening writes those guarantees down and makes the build prove them.
- **11 features change** (H1). The biggest three: the Dealer Pack loses its assistant, remote content updates are dropped, and purchases wait for ad money and a CA.
- **12 pieces of work become explicit features** (H2). They cost **+1 slice before the first tester**, so the first public player moves from 15 to **16 weeks**. Ads and purchases get +1 slice each.
- **v1 collects nothing and ships without the internet permission**, so it can't send anything anywhere (H3).
- **No server of ours, and none is added** (H4):
  - Where cheating only changes the cheater's own single-player game, the rule stays on the phone.
  - Ad limits and what can be sold move to Google's servers.
  - Purchase checks, consent records and age checks stay on the phone as accepted risks, each with its price (H5).

### H1. What changes in the feature set

| ID | Feature | Was | Now | Forced by |
|---|---|---|---|---|
| H1-1 | Dealer Pack (₹149) | Starter assistant + cosmetic theme | **Cosmetic only:** a theme set and a stall nameboard style | An assistant earns in-game cash, and §2 forbids selling "anything that converts to it". Selling a progress item also needs a lawyer before the first sale (RESEARCH L-1, ADV-4). It weakens an offer that was already thin (Business verdict §1) — accepted |
| H1-2 | Purchases — when | Right after G3, ≈ May 2027 | After **AdMob's first payout**, then a **CA's GST answer** paid from it, then the grievance + refund page. Best case ≈ August 2027 | Your decision, 2026-11-24 ("let it make money first, then call CA"), on top of RESEARCH L-10: no sale until the GST position is confirmed |
| H1-3 | Content updates (LATER) | Fetched as JSON from GitHub Pages | **Dropped.** Content ships only inside app updates, which Play reviews | A hijacked GitHub account could push any line to every player overnight. Fetching would also give every player's IP address to GitHub, and an offline game would need the internet. §7 already says "not a live service" |
| H1-4 | Internet in v1 | Unstated | **No `INTERNET` permission in v1**, enforced by the build guard (H2-8). It arrives with ads | RESEARCH L-4 and §3 #3: the Data safety answer must match the build. A game that can't connect can't leak |
| H1-5 | Age screen — when, and what it keeps | "Before any ad" | At the **first tap on an ad offer**, never at launch. It keeps **adult/minor and the date**, never the birth year. "Minor" can be re-checked once a year in Settings | RESEARCH L-2, L-3 (neutral screen); R4 (the first haggle stays under 30 seconds); H3 |
| H1-6 | Rewarded ads | Peek, instant restoration, reopen a haggle | Same, plus: **at most 12 a day**; a reward only for a completed ad; **the game saves before every ad**, so a failed or interrupted ad costs nothing | RESEARCH R11, L-6 (invalid traffic suspends AdMob accounts); COMPETITORS §3 theme 3 (lost progress clusters around ad playback) |
| H1-7 | Ad content limits | Gambling and real-money-game categories blocked, max rating "T", set in code | Also set **in the AdMob console**, where a modified app can't undo them. Players' "bad ad" reports are checked in AdMob's ad review center within a week | RESEARCH X4 (showing a real-money-game ad is an offence), L-6 |
| H1-8 | Deal card | "bought ₹200 → sold ₹1,800" | Same, with the coin icon and **"Game money — no real money"** on the card | RESEARCH L-1 (expecting a money reward is a money-game test), R20. ₹ figures on a shared card are easy to misread as an "earn money" app |
| H1-9 | Notifications (LATER) | Optional, with an explainer | Same, plus: **scheduled on the phone** (no push service, no token); only for something the player started; **at most one a day**; never about ads or purchases | RESEARCH L-9 (nagging is a dark pattern); H3 |
| H1-10 | Auto Backup | Backs up the game | Backs up **saves and settings only**. Age and consent records stay on the phone, so a restored or new phone asks again | RESEARCH L-2 (consent belongs to one notice and one person), S30 |
| H1-11 | Cloud save and achievements (Play Games, LATER) | Deferred | Still deferred, and **blocked until the data table is redone**: they would give us the player's Play Games ID | H3 |

### H2. What we must now build that we had not planned

| ID | Feature | What it is in this game | When | Time |
|---|---|---|---|---|
| H2-1 | Legal pages | **Now:** the waitlist page's privacy lines (built 2026-11-24). **v1:** a privacy policy written only from the data table (H3), linked in the app and in Play Console; credits & licences in Settings. **Ads:** policy v2 (Google Mobile Ads data, Google as recipient, the age screen, consent and withdrawal, DPDP rights with a 90-day reply, a contact person); the ads notice as Tamil and Hindi web pages; app-ads.txt. **Purchases:** terms, refund policy, grievance page | Now → purchases | v1: in slice 16 (already counted). Ads and purchases: in their new slices |
| H2-2 | Consent screen | Adults only, after the age screen, at the first ad tap: what Google collects and why; *Allow & watch* / *Not now*; nothing pre-ticked. **Settings → Ads & privacy** withdraws it: ad requests stop at once, and the SDK doesn't start at the next launch. A new notice version asks again | Ads | M4 slice 1 |
| H2-3 | Age gate | A neutral year-of-birth screen: nothing pre-filled, no hint of the age needed. Under 18 or no answer → the ads SDK never starts, and ad offers become one free "Periyappa's tip" a day. Nothing in the game is locked | Ads | M4 slice 1 |
| H2-4 | Account deletion | There are no accounts. **Settings → Start over** deletes everything the game keeps on the phone: the save, both backups, settings, and the age and consent records. Android's next backup replaces the Drive copy. Waitlist and support emails are deleted on request (H2-11) | v1 | Start over was already planned (slice 12); the full wipe and its test go in new slice 15 |
| H2-5 | Data export | **v1:** Settings → Share my stats (already planned): your numbers as text, through the share sheet. **Ads:** Settings → **Your data**: everything kept on this phone (progress summary, settings, adult/minor, consent choice + date + notice version), with Share and Start over, and a link to Google's own tools for what Google holds | v1, ads | Ads: ~2 h in M4 slice 3 |
| H2-6 | Moderation tooling | **Nothing to build** — players can't post, name, chat or trade. Two outside things reach them: **our own lines** (the validator, a human read, Report this line) and **ads** (AdMob's ad review center, fed by players' reports, checked weekly while ads run) | v1, ads | ~15 min a week once ads run |
| H2-7 | Report button | **Settings → Report a problem.** Pick a reason (bug · lost progress · a line that offends · a bad ad · a purchase), read the whole message, then your email app opens. It carries the app, save and Android versions — no IDs. **Long-press any line → Report this line.** It's also how closed testers send feedback | v1 | ~2 h, new slice 15 |
| H2-8 | Build guard | The build fails if the merged manifest asks for any permission off the allowlist. v1: only `VIBRATE`. Ads add `INTERNET`, `ACCESS_NETWORK_STATE` and `AD_ID`; purchases add `BILLING`; notifications add `POST_NOTIFICATIONS`. A second check fails any non-release build that contains a real ad unit ID | v1, extended at each stage | ~1 h, new slice 15 |
| H2-9 | Crash note | After a crash, the phone keeps the last three stack traces, with no IDs. They leave only inside a Report a problem the player chooses to send. Play Console's Android vitals covers the rest, with no SDK | v1 | ~1 h, new slice 15 |
| H2-10 | Audit trail | **On the phone:** the consent record. **In git:** every dated version of the privacy policy and the ads notice (your GitHub Pages history), and this file's decisions. **A private requests log** (a Google Sheet): date in, acknowledged, done. It covers removals and data questions now, and complaints once purchases ship (48 h / 1 month; DPDP: 90 days) | Now | ~15 min to set up |
| H2-11 | Contact and deletion promise | One contact email for the game, ideally a separate free Gmail rather than your personal address, used on Play, on the page and in the policy. Removals within 7 days, logged in H2-10 | Now, before the page goes live | Process |
| H2-12 | Accounts and keys | 2-step verification on the Google account (Play Console, AdMob, the form) and on GitHub (it hosts the policy, app-ads.txt and the page). Play App Signing on. The upload key and its password go in a password manager plus an offline copy; keystores never go in git | Before the first upload | ~30 min, once |

**What it costs in time** (the Business verdict's §3 is updated to match):

| Stage | Was | Now | Added |
|---|---|---|---|
| First version | 16 slices | **17.** New slice 15: Report a problem, Report this line, crash note; Start over's full wipe; permission and backup checks | ≈ 1 week before the first public player (15 → 16 weeks) |
| Ads (Milestone 4) | 2 slices | **3:** age screen + consent; rewarded placements, cap, save-before-ad, AdMob console; Your data, privacy policy v2, Tamil/Hindi notice pages, app-ads.txt, Data safety update | ≈ 0.8 week; ads live ≈ 10 April 2027 |
| Purchases | 2 slices | **3:** billing (pending, acknowledge, restore, re-lock refunds); shop + cosmetic catalogue; terms, refund policy, grievance page + Help entry | ≈ 0.8 week, inside the wait for ad money |
| You, once | — | Game email, 2-step verification, key backup, requests log | ≈ 1 hour |

### H3. The data minimisation pass

**Field by field — what we cut:**

| Field | Where it was planned, or tempting | Why it's cut |
|---|---|---|
| Name, phone number, city | Waitlist | One email needs only an address |
| Google sign-in on the form ("verified" emails, one response each) | Waitlist | It hands over Google account details; a typed email is enough |
| Birth year | Age screen | Adult/minor answers the question; the date allows a yearly re-check |
| An analytics SDK (Firebase / Google Analytics) and install IDs | Measuring G2 and G3 | Play Console (installs, retention, ratings), testers' Share my stats and AdMob's own reports cover every gate |
| A crash-reporting SDK | Debugging | Android vitals plus the opt-in crash note (H2-9) |
| Names for the player or the shop; any free text | Personalisation | Nothing typed means nothing personal stored, nothing to moderate, and nothing offensive on a share card |
| Push-notification tokens | Notifications | Notifications are scheduled on the phone (H1-9) |
| Remote content requests | Content updates | Dropped (H1-3) |
| Play Games player ID | Cloud save, achievements | Deferred (H1-11) |
| Purchase tokens after use | Purchases | The app asks Google Play what's owned at each launch and keeps only the owned item IDs |
| Other ad networks (mediation) | More ad money | Each one is another company receiving player data (RESEARCH L-4) |
| Testers' names in the feedback log | Closed test | Play asks what testers said, not who said it; use tester numbers |
| Location, contacts, files, camera, microphone | — | Never asked for |

#### THE DATA TABLE

Everything that survives the cut. **The privacy policy and Play's Data safety form are written from this table and nothing else.** A new SDK, field or form changes this table first.

**SDKs in the app:**
- **v1:** libGDX and AndroidX, which send nothing.
- **Ads:** adds the Google Mobile Ads SDK.
- **Purchases:** adds the Google Play Billing Library.
- **Never:** no analytics, crash, attribution or mediation SDK.

| What | Why | Kept where | Who else receives it | How long | Allowed by | On Play's Data safety form |
|---|---|---|---|---|---|---|
| **Now — the waitlist and your inbox** | | | | | | |
| Email address | Send one email: the launch, or that it won't launch | Google Forms, in the game's Google account | Google (runs Forms) | Deleted within 30 days of that email; removed on request within 7 days | Consent — they submit it for that purpose | — (a web form, not the app) |
| Preferred voice (optional) | Choose which voice to polish first | Same | Google | Same | Consent | — |
| "I'm 18 or older" tick | Keep children's emails off the list | Same | Google | Same | Consent | — |
| Visitors' IP address and browser details | Serve the page and the form; security | GitHub's and Google's servers; we never see them | GitHub (hosts the page and logs IPs "for security purposes" [H-S1]); Google (Forms) | Their own retention | Their terms | — |
| Requests log: who asked (email), what, when, what was done | Keep the promises (7 days; 48 h / 1 month; DPDP 90 days) and show it | A private Google Sheet | Google | 12 months after closing | Legal duty | — |
| **v1 — the game** | | | | | | |
| Game progress: cash, stock, reputation, day, stats | Play and resume | The phone, plus 2 local backups | Android Auto Backup copies it to the player's own Google Drive; we can't read it [S30] | Until Start over or uninstall | The game needs it | No — it stays on the device [S17] |
| Settings: voice, sound, text size | Same | Same | Same | Same | Same | No |
| Deal card image; Share my stats text | The player chooses to share | Leaves only through Android's share sheet | Whoever the player sends it to | Not ours | The player's own action | No — user-initiated [S17] |
| Report or support email: the sender's address, the message, and anything they chose to include (versions, crash note) | Reply and fix | The game's mailbox | The mail provider (Google, if Gmail) | 12 months after the last message | Sent voluntarily for that purpose (DPDP s.7(a), RESEARCH L-2) | No — sent from the player's own email app |
| Closed testers' email addresses | Run the closed test | Play Console's tester list | Google | Removed after production launch | They agreed to test | — |
| Crash and freeze reports | Fix crashes | Play Console (Android vitals) | Google, which collects them through Android from players who allow usage & diagnostics | Google's retention | Google's own collection | No [A — confirm against Play's Data safety guidance at submission] |
| **Ads (Milestone 4)** | | | | | | |
| Adult/minor and the date asked — not the birth year | Keep ads away from under-18s | The phone; never backed up | Nobody | Until Start over or uninstall | The game needs it to obey the law | No — on the device |
| Ad consent: choice, date, notice version | Ads only with consent; proof on the phone | The phone; never backed up | Nobody | Replaced at each change; until Start over or uninstall | Same | No — on the device |
| Rewarded ads watched today | The 12-a-day cap | The phone | Nobody | Reset daily | The game needs it | No |
| What the Google Mobile Ads SDK "collects and shares" automatically: IP address (approximate location); product interactions (launches, taps, video views); diagnostics (launch time, hang rate, energy use); advertising ID, app set ID and, where applicable, identifiers of accounts signed in on the device. Encrypted in transit [S18] | Pay for the game with ads players choose | Google's servers | **Google (AdMob)** — no mediation, no other network | Google's retention | **Consent, adults only** (H2-2, H2-3) | **Yes — collected and shared**, for advertising, analytics and fraud prevention, from Google's own mapping [S18], in the same release as the SDK |
| **Purchases** | | | | | | |
| Order number, item, price, date | Deliver, restore and refund | Google Play; you see orders in Play Console. The app keeps only which items are owned | Google Play | Google's records; owned items until uninstall | The contract — the player buys | Whatever Play's guidance requires at that release [Q] |

**What the forms say, from this table:**
- **Privacy policy v1 (before the closed test):** the waitlist and v1 rows. The app collects nothing; no ads, analytics or accounts; Auto Backup; shares; emails kept 12 months; not for children; the contact address.
- **Data safety, v1:** "No data collected or shared"; ad ID declaration "No" (RESEARCH L-4, unchanged).
- **At ads:** policy v2 adds the ads rows. Data safety declares the Google Mobile Ads row, and the ad ID declaration becomes "Yes", in the same release as the SDK (RESEARCH §3 #3).

### H4. Where each rule is enforced

We have no server, and this pass doesn't add one.

**Where a rule can live:**
- **SERVER** means Google's servers (Play Console, Play Billing, AdMob). They enforce what we set there, and no modified app can change it.
- **DEVICE** means our code on the phone. A player with a modified app can get around it.
- **BUILD** means a check that stops a bad release from being made at all.
- **YOU** means a routine you follow.

**The rule of thumb:** if getting around a rule only changes the cheater's own single-player game, it stays on the phone. If it touches money, children, the law or other people, it goes to Google's servers where Google offers that. Otherwise it's an accepted risk (H5), with its price.

**Moved to Google's servers in this pass:**
- **Ad content limits** go into the AdMob console as well as the code (H1-7). A modified app could strip the code setting, but it can't change the console.
- **What can be sold, and at what price,** comes only from Play Console's product list. The app shows Play's prices and never hard-codes them (H4-8, H4-9).
- **The daily ad cap** also goes into AdMob as frequency capping on the rewarded unit, if the console offers that for rewarded ads [A — check at Milestone 4].

**Kept on the phone,** with the price of moving each in H5: purchase checks (H5-11), consent records (H5-7) and age checks (H5-6).

| ID | Rule | Where | Who could get around it, and how | The test that proves it holds |
|---|---|---|---|---|
| H4-1 | v1 sends nothing anywhere | BUILD + DEVICE (Android blocks network use without `INTERNET`) | Nobody with our build | The build guard (H2-8) fails on any permission off the allowlist. On the release bundle, `aapt dump permissions` lists only `VIBRATE` |
| H4-2 | No ads SDK for under-18s or an unknown age — and nothing in the game locked for them | DEVICE | A minor who lies about the year, or starts over and lies (H5-6) | Unit: the ad gate opens only for adult + consent + current notice version, across every combination. Device: enter a 2010 birth year → a fake ad service records zero starts, and offers become Periyappa's tip |
| H4-3 | No ad without consent; withdrawing stops ad requests at once | DEVICE | Only the player, against their own interest | Unit: the consent states. Device: allow → withdraw → no ad requests; relaunch → SDK not started; bump the notice version → asked again |
| H4-4 | Testers and debug builds never show real ads | BUILD | A tester who later installs the public build and taps ads | Build: non-release builds hold only Google's test ad unit IDs, and real IDs sit in a local file outside git. Testers are told in writing |
| H4-5 | A reward only for a completed ad, once | DEVICE | A modified app that grants rewards without ads — only in its own game. No ad is billed, so nobody loses money (H5-1) | Unit: rewards come only from the completion callback; closing early gives nothing. Device: kill the app mid-ad → nothing granted, nothing lost |
| H4-6 | At most 12 rewarded ads a day | DEVICE, and SERVER if AdMob offers frequency capping on rewarded units [A] | A clock change or a modified app. Bots are invalid traffic, which Google filters (H5-10) | Unit: the 12th plays, the 13th is refused; clock set back → count kept; next day → reset |
| H4-7 | No gambling, real-money-game, loan or adult ads | SERVER (AdMob console) | A misclassified ad (H5-9) | Release check: a screenshot of the console's blocked categories and max rating "T"; the code also sets "T"; every "bad ad" report reviewed within a week |
| H4-8 | Nothing for sale converts to in-game cash; no paid randomness | SERVER (Play Console's product list) + DEVICE | Nobody: a product missing from Play Console can't be bought | Unit: every product ID maps to a cosmetic, and granting any product leaves cash, stock, reputation and progress unchanged. Release check: Play Console's list = the cosmetic list |
| H4-9 | Prices shown = prices charged | SERVER (Google Play) | Nobody | Unit: no ₹ price literal in the shop code; the shop shows Google Play's own price text |
| H4-10 | Grant only paid purchases; acknowledge within 3 days; restore; re-lock refunds | SERVER (Google Play keeps the record) + DEVICE (asks Play at each launch) | A cracked app that fakes Google Play's answer unlocks cosmetics (H5-11) | Play's license testers with test cards: "always approves", "always declines", and a slow card (shows "payment pending" and grants nothing until paid). Refund a test order → re-locked at the next launch. No order auto-refunded after 3 days |
| H4-11 | Saves are never lost or corrupted | DEVICE | Editing your own save only cheats your own game (H5-1) | Kill during a write → the backup loads. Corrupt file → "restored yesterday's backup". Full storage → "couldn't save", old save intact. A migration test for every save version |
| H4-12 | Start over leaves nothing behind | DEVICE | — | Device: Start over → app files and preferences empty; the next launch is a first launch |
| H4-13 | Age and consent records are never backed up | DEVICE (backup rules) | — | Unit: parse the backup rules; both files excluded. Device: back up, restore on a second phone → save restored, age and consent asked again |
| H4-14 | Offline timers can't be skipped by clock changes (LATER) | DEVICE | Changing the clock — only in their own game (H5-1) | Unit: clock forward → capped gain; clock back → ignored, never punished |
| H4-15 | No line breaks the content rules (deities, brands, stereotypes, gambling words) | BUILD (validator) + YOU (a human read) | A line that passes both | Validator tests with banned words in all three voices; the build fails on a hit. A Report this line → fixed in the next update |
| H4-16 | Sharing sends nothing by itself | DEVICE (Android's share sheet) | — | Device: Share opens the chooser; cancelling sends nothing |
| H4-17 | The listing and Data safety match the build | YOU (release checklist) + SERVER (Google's review) | Forgetting after adding an SDK | Checklist: merged-manifest permissions ↔ Data safety answers ↔ the data table. The listing has no "no ads", "earn", "win cash", "approved" or "registered" |
| H4-18 | Waitlist emails used once, then deleted; removals within 7 days | YOU | You, by forgetting | A dated reminder; each deletion recorded in the requests log (H2-10) |
| H4-19 | Anything shared between players gets a server first | Design rule | — | If leaderboards, gifts, trading or prized events are ever proposed, their rules go on a server before anything else. That's a redesign, not a feature |

### H5. The things we are accepting

**For v1:**

| ID | The risk we live with | Why — and what the safe option would cost |
|---|---|---|
| H5-1 | Cheating in your own game: edited saves, clock changes, modified APKs | No server of ours. Tampering only changes the cheater's single-player game. A server to stop it would cost money every month and need accounts — the opposite of the product |
| H5-2 | Auto Backup copies saves to the player's Google Drive | It's Android's feature, under the player's Google account, and we can't reach the copy. Turning it off would lose progress on a new phone — a top complaint in this genre (COMPETITORS §3, theme 3) |
| H5-3 | Fewer crash reports (no crash SDK) | Android vitals covers players who share diagnostics; the crash note covers players who report. A crash SDK would put data collection on the Play form |
| H5-4 | Google holds the waitlist (Forms) and, with Gmail, the support inbox | There's no free alternative without our own server. The page says so |
| H5-5 | One Play account and one upload key are the only way to ship updates | 2-step verification and key backups (H2-12) reduce it, and Play App Signing lets Google reset a lost upload key. A second store (Indus, RESEARCH R30) waits until there's something to protect |

**When ads and purchases arrive — decided now, so none of them comes as a surprise:**

| ID | The risk we live with | Why — and what the safe option would cost |
|---|---|---|
| H5-6 | A minor lies at the age screen and gets ads | Real age checks need ID documents: they cost money and collect far more sensitive data. Google's Restrict Minor Access would block every account Google thinks is a teen, and reach is the number the business case hangs on. A neutral screen is the standard; ADV-1 confirms it's enough by May 2027 |
| H5-7 | Consent proof lives only on the player's phone | Sending consent records to a server needs a server and an install ID — more data about every player, not less. We can show the design, the code and every dated notice version. ADV-1 checks |
| H5-8 | The ads notice exists in English, Tamil and Hindi only | DPDP lets a player ask for a notice in any of the 22 scheduled languages [S1 s.5(3)]. Translating all 22 costs money we don't have. ADV-1 checks |
| H5-9 | A gambling, loan or real-money-game ad slips past AdMob's categories | Blocking is Google's job; we add reports and a weekly review (H2-6). The only sure fix is no ads, which ends the money |
| H5-10 | Invalid traffic (bots, modified apps) gets the AdMob account suspended | The cap, rewards only for completed ads, and test ads for testers lower it; Google's filters do the rest |
| H5-11 | Cracked apps unlock cosmetics without paying | Checking purchases on a server means our own server, 2–3 slices and a Google service-account key to guard. It would only protect buyers who'd crack the game instead of paying — a slice of a likely ~₹300 a month (402 installs × 0.5% × ₹153) |
| H5-12 | Your home address on the Play listing, once purchases ship | Your decision, 2026-11-24. A business address costs money |
| H5-13 | Purchases may wait a long time | Your rule: no CA until ads pay out (≈ ₹9,293). In the likely case that takes years, so the likely case stays ads-only. If the June 2027 checkpoint passes but AdMob hasn't paid by July, paying a CA from your pocket is the only way purchases make November (Business verdict §8) |

**Not accepted — these block a release instead:**
- Hinglish lines without a native read (open question 4, before the closed test).
- A Data safety form that doesn't match the build (H4-17).
- Any real ad in a tester build (H4-4).

**Sources opened for this section (2026-11-24):**

| ID | Source | What it gave |
|---|---|---|
| S18 (re-read) | [AdMob — Google Play data disclosure](https://developers.google.com/admob/android/privacy/play-data-disclosure) | The Google Mobile Ads SDK (Legacy) 25.5.0 "collects and shares" data automatically "for advertising, analytics, and fraud prevention purposes": IP address, user product interactions, diagnostics, and device and account identifiers. It's encrypted in transit, and ad ID collection can be turned off in the manifest |
| H-S1 | [GitHub Docs — About GitHub Pages](https://docs.github.com/en/pages/getting-started-with-github-pages/about-github-pages) | "the visitor's IP address is logged and stored for security purposes, regardless of whether the visitor has signed into GitHub or not" |
