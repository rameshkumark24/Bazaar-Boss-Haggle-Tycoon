# Bazaar Boss: Haggle Tycoon — the product as decided

> **Status:** agreed 2026-11-23. Legal and store changes from [RESEARCH.md](RESEARCH.md) §0 folded in the same day. This file is the source of truth. Every later step reads it, not the chat. Change the product here.
>
> **Labels:** **[F]** FACT — you said it, or you accepted my recommendation ("decided 2026-11-23") · **[A]** ASSUMPTION — inferred; correct it before it becomes the product · **[Q]** OPEN QUESTION — nobody knows yet · **[✓]** CHECKED — verified against a source (sources and tiers in [RESEARCH.md](RESEARCH.md))

## In one paragraph

A single-player, offline Android game set in Indian bazaars. You buy stock at kabadi auctions and from walk-in sellers, spot the fakes, and sell by haggling one-on-one with customers who hide their walk-away price but show their mood. Profit grows a footpath stall into a shop, a showroom and an antique gallery, city by city. Characters talk the way bazaars talk — in Tanglish or Hinglish — and the game is fair, readable and never a gamble. [F]

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
- **Money:** free; optional rewarded ads, capped interstitials, small non-random purchases. Never real-money stakes. [F]
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
- Restoration on timers raises value. [F] (after v1)
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
- Progress survives reinstall or a new phone: Android Auto Backup configured + two rotating local backups. [A]
- Credits & licences (fonts, sounds, CC BY icons). [A]
- Privacy policy link inside the app. [✓ — required by Play even when nothing is collected]
- Indian number grouping (1,25,000) and lakh/crore short forms (₹2.5L, ₹1.2Cr); ₹ shown with a stylised coin icon, never banknote images. [F for no banknotes; rest A]
- Mood shown as face + word, never colour alone. [A]
- Once purchases exist: restore purchases, "payment pending" state (UPI). [A]
- Once timers exist: optional notifications, with an explainer before Android's permission prompt. [A]

### DIFFERENTIATING — why someone picks this over Bid Wars or Dealer's Life
- **Indian bargaining as mechanics, not decoration:** the "Last price?" ritual; walk away and get called back ("Okay okay, vaanga!" / "Achha theek hai, aao!"); "add the small lamp and I'll take it" throw-ins; bohni / போணி — the first sale of the day. [A]
- **Characters who talk like the bazaar:** code-mixed Tanglish and Hinglish banter, short and funny. [F]
- **Fair and readable:** after each deal, one line on what you left on the table ("He'd have gone to ₹380"); after each auction, every rival's estimate revealed. Answers the "rigged!" reviews auction games get. [A]
- **Appraisal you learn:** clues, a magnifier upgrade, a Dealer's Guide that fills as you discover items. [F + A]
- **Restoration you can see:** dust and rust layers peel off the item. [A] (after v1)
- **Indian seasons as gameplay:** Diwali rush, Pongal clearance, wedding-season brass and silver demand, monsoon godown damage — on an in-game calendar, not real dates. [F; calendar A]
- **Respectful by design:** offline, small download, runs on a 2–3 GB phone, ads never mid-haggle, no dark patterns (§3.2). [F + A]

### LATER — real, deliberately deferred
- Restoration timers, assistants, offline progress, notifications. [F, deferred]
- More cities (Chennai, Madurai, Mumbai, Delhi) with their own item pools and customer types. [F, deferred]
- Collections (radios, brass ware, clocks, toys) with set bonuses; events. [F, deferred]
- Content target 150+ items, 15+ customer types. [F, deferred]
- Native-script Tamil and Hindi UI (needs the Indic text renderer). [Q — wanted at all, now that voices are Tanglish/Hinglish?]
- **Money (Milestone 4)** [F unless marked]:
  - Rewarded ads, opt-in only: (a) extra 10-second peek, (b) finish one restoration instantly, (c) reopen a haggle after a walk-away, once per customer.
  - Interstitial at most once per 3 completed auctions; never mid-haggle; none in a player's first 10 minutes. **Shown only after the day-end kanakku screen, and only to adults who consented.** [✓ RESEARCH L-5]
  - **Age check and consent before any ad** [✓ RESEARCH L-2, L-3]:
    - A neutral year-of-birth screen comes before the ads SDK ever starts.
    - Under 18 or age unknown → the ads SDK is never initialised. The game stays fully playable; rewarded perks become a daily in-game "Periyappa's tip".
    - 18+ → a plain notice at the first "watch an ad" tap, naming what Google collects. Buttons: *Allow & watch* / *Not now*.
    - Consent is saved on the phone and can be withdrawn in Settings → Ads & privacy.
    - Tamil and Hindi versions of the notice are web pages on GitHub Pages.
  - Consent for EEA/UK (Google UMP) — only if we ever ship there. [A]
  - AdMob setup: test ads for every tester build; gambling and real-money-game categories blocked; max ad content rating "T"; app-ads.txt on GitHub Pages. [✓ RESEARCH L-6]
  - Purchases: Remove ads ₹99, Dealer Pack ₹149 (starter assistant + cosmetic theme), cosmetic themes ₹49; restore purchases.
  - No paid randomness, ever. **Never sell in-game cash** or anything that converts to it, and never let players trade items for money. [✓ RESEARCH L-1 — these are the regulator's money-game tests]
  - **Ads-only first.** Purchases wait for three things: your decision on the address Google shows for merchants, a CA's answer on GST, and a grievance + refund page. [✓ RESEARCH L-7 to L-10]
  - Google test ad unit IDs until you say "release". [F]
- Cloud save (Play Games), achievements, voice lines, more languages, recurring-character story arcs, content updates fetched as JSON from GitHub Pages. [A]
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
| 11 | **Sharing a win** | Pride, plus social proof for new players | "Deal card" image (bought ₹200 → sold ₹1,800) shared via Android's share sheet to WhatsApp. Shared only when the player taps; no personal data. | ✓ (first to cut if late) |
| 12 | **Effort raises value** (IKEA effect) | We value what we fixed ourselves | Restoring items with visible wear coming off | later |
| 13 | **Fresh starts** | New chapters invite lapsed players back | New tier or city = new chapter. Returning players get "Welcome back — here's a cheap starter lot", never a guilt message. | ✓ |

**Never, by design** [A]:
- No fake urgency or countdowns on purchases.
- No guilt-trip buttons ("No thanks, I like losing money").
- No streaks that punish missed days.
- No energy systems that block play.
- No nagging for ratings or purchases (at most one in-app review prompt, after a big win).
- No ads disguised as game content.
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
| In-app account deletion | N/A — no accounts. Equivalent: Settings → Start over. [A] |
| Restore purchases | When purchases ship: automatic at launch + Settings button + "payment pending". [A] |
| Privacy policy / terms in-app | Privacy policy: yes, v1. [✓] Terms, refund policy and grievance page: when purchases ship. [✓ RESEARCH L-14] |
| App version in Settings | Yes, plus save-format version. [A] |

**All screens** (v1 column = first version, §8)

| # | Screen | What it's for | v1 |
|---|---|---|---|
| 1 | Voice picker | First launch: English / Tanglish / Hinglish, each with a sample line | ✓ |
| 2 | Stall (home) | Cash, reputation, day, display slots, next goal bar, Today's special; routes to everything | ✓ |
| 3 | Haggle | Face, mood, patience, item, slider, quick buttons, round history with reasons | ✓ |
| 4 | Deal result | Profit, verdict line, reputation change, share deal card | overlay |
| 5 | Inspect item | Clues, condition, fake suspicion | ✓ |
| 6 | Auction | 10-second peek → live bidding → result with each rival's estimate | ✓ |
| 7 | Lot reveal & sort | Gunny-bag reveal; keep or scrap | ✓ |
| 8 | Godown | All items, filter/sort, both empty states, "found X of Y" | ✓ |
| 9 | Day end (kanakku) | Best deal first, day's profit, reputation, tomorrow's teaser, upgrade | ✓ |
| 10 | Settings | Voice, sound, text size, privacy policy, credits, version, start over, share my stats | ✓ |
| 11 | Workshop | Restoration slots, timers, costs, value gained, instant-finish (ad) | |
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
| 24 | Shop (purchases) | Remove ads, Dealer Pack, themes, restore, pending | |
| 25 | Ad offers | Extra peek, instant finish, reopen haggle; "no ad now" state | |
| 26 | Age screen + ads consent | Neutral year-of-birth screen before any ad; plain notice at the first "watch an ad" tap; withdraw in Settings (RESEARCH L-2) | |
| 27 | Notification explainer | Shown before Android's one-shot permission prompt | |
| 28 | Save recovered | "Couldn't read your save; restored yesterday's backup" | |
| 29 | Storage full | "Couldn't save — phone storage full" | |
| 30 | Credits & licences | Fonts, sounds, icons | in 10 |
| 31 | Rate prompt | Google's in-app review, after a big win only, at most once | |
| 32 | Dev: text & content test | Fonts, ₹, number formats, validator results (Milestone 0) | dev |
| 33 | Dev: haggle lab | Tune customers live (Milestone 1) | dev |

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
- **Not online:** no accounts, multiplayer, chat, trading between players, or server. [F + A]
- **Not an idle clicker:** no tapping for money; offline progress only for restoration and assistants, capped. [A]
- **Not a story game:** one-liners, no cutscenes. [A]
- **Not a real antiques guide:** invented items and values; no real brands, people, idols or coins. [F + A]
- **Not for children:** declared 18+ on Play, outside Google's Families programme. Content stays teen-suitable, and no ad ever reaches an under-18 player. [✓ RESEARCH L-3 — changed from 13+ on 2026-11-23]
- **Not a live service:** content ships in app updates. [A]
- **Not pay-to-win:** purchases never sell what normal play earns in reasonable time. [A]

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
- Under the hood [F, trimmed]: JSON content with the validator, string table, unit tests for haggle and auction maths and for save/load migration, and a desktop headless haggle simulator.

**Out — waits for G2:**
- Restoration, assistants, offline income, notifications.
- Other cities, events, collections, Dealer's Guide.
- Magnifier and other upgrades.
- Rival bluffing, godown auctions.
- Ads, purchases, consent.
- Themes, achievements, cloud save, native-script UI.

**Screens: 9** — voice picker, stall, haggle, inspect, auction, lot reveal, godown, day end, settings — plus overlays for the deal result, tier-up, low cash and hints. Of the commonly forgotten screens, only the **privacy policy link** is store-required for v1. [✓]

**Build order** (each milestone ends with: what to test on your phone, known issues, wait for "go") [F for the gating; order A]

| Step | Contents |
|---|---|
| M0 | New libGDX 1.14.x project; content schemas (item, customer, market, event); validator; string table; save system; Indian number formatting; dev text/content screen |
| M1 | Haggle engine + haggle lab + unit tests + simulator bots → **G1** |
| M2-lite | Auction lite, lot reveal, inspection, scrap |
| M3-lite | Day loop, stall → shop → showroom goal, godown, kanakku, settings, retention hooks, low-cash floor |
| M5-lite | Privacy policy on GitHub Pages (RESEARCH L-14), credits & licences, store listing (RESEARCH §3), Data safety ("no data collected"), ad ID declaration "no", target audience 18+, content rating, closed test with ≥ 12 of 14 testers opted in for 14 days → **G2** |
| After G2 | Public release in India. Then ads-only Milestone 4 (rewarded first), under the rules in RESEARCH.md → **G3** |
| After G3 | Restoration, assistants, offline income; more cities; events; collections; purchases once the address question is settled |

---

## 9. Your bar

| Question | Answer |
|---|---|
| **What you want back** | Money and users. [F] Users come first, because ad money only follows retained users. [A] |
| **What you can put in** | Time — yes, if it proves worth it. [F] Hours per week: [Q], so no dates are promised yet. Money: nothing beyond the Play account you already have. [F] |
| **What you already have** | A Google Play developer account. [F] Type (personal/organisation) and creation date [Q]; assumed personal and created after 13 Nov 2023, so the 12-tester / 14-day rule applies. [A] 14 testers. [F] A private GitHub repo for this project and a GitHub Pages site (rameshkumark24.github.io) to host the privacy policy. [✓] Skills, market knowledge and a 2–3 GB test phone: [Q]. |
| **Where you sell first** | Google Play [F], India only at first [F — decided 2026-11-23]. Prices in ₹. |
| **What it may cost** | Free tools and free tiers only. [F] No more investment without returns. [F] Play account already paid. [F] Apple: not planned. [A] |

## Open questions (consolidated)

1. Where are the idle engine and the Bus Mudhalali text code — and are they still wanted, given Latin-script voices?
2. Hours per week you can put in, and for how many weeks.
3. Play account: personal or organisation; created before or after 13 Nov 2023?
4. Who reads the Hinglish lines as a native speaker?
5. When purchases arrive, may Google show your address on the Play listing — or stay ads-only?
6. Is native-script Tamil/Hindi UI still wanted later?
7. Can you draw (for art direction), and do you have a 2–3 GB RAM phone to test on?
