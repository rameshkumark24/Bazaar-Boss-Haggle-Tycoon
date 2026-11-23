# Competitors — who sells the haggle-and-flip fantasy today, and where they leave a gap

> **Status:** 2026-11-24. Reads [IDEA.md](IDEA.md) for the product. Research only — nothing here changes IDEA.md yet.
>
> **Labels on every claim:**
> - **SEEN** — I opened the page myself
> - **REPORTED** — someone else says so; I didn't open the source, or it's another party's claim
> - **ESTIMATED** — a tool's guess, or mine
>
> **IDs:** `[C#]` resolves to a link and the date seen in §10.
>
> **How the evidence was gathered:**
> - **Store pages:** the Google Play India store, read in English through your Chrome on 2026-11-24.
> - **Reviews:** read in Play's own review dialog with the **3-star filter**. Scripts did not touch Play's review endpoints, which Play's robots.txt disallows.
> - **AppGoblin:** a free app-intelligence site that publishes install, active-user and revenue *estimates*.
> - **Google Trends:** read on trends.google.com in the browser.
>
> **What I could not reach:**
> - **Reddit:** it blocks scripts, and the browser tool refuses the site.
> - **AppMagic:** needs your login.
>
> Both are listed as tasks for you in §9.
>
> **Why no parallel subagents:** review mining has to go through your single Chrome window, and you had already hit your usage limit twice in this session. So the competitors were researched one after another, each with the same method, independently of the others.
>
> **Copyright note:** store reviews belong to their authors. Complaints are paraphrased and attributed (reviewer, review date, store page) so you can check each one. There is one short direct quote.

---

## 1. Who they actually are

### Bid Wars 1 & 2 — the auction-first giants

| | Bid Wars 1: Auction Simulator | Bid Wars 2: Auction Simulator ("Pawn Empire") |
|---|---|---|
| What it does | Storage-unit auctions against rivals, then a pawn shop | The same, plus city building; describes itself as a storage-auction / pawn-shop / business-empire game [C1 SEEN] |
| Developer | "By Aliens L.L.C-F.Z" [C2 SEEN] | Same [C1 SEEN] |
| Model | Free; ads + purchases [C2 SEEN] | Free; ads + purchases from **₹5 to ₹9,800 per item** [C1 SEEN] |
| Size on Play | 10M+ downloads, 4.2★, 31.2 lakh reviews [C2 SEEN] | 10M+ downloads, 4.3★, 15.2 lakh reviews [C1 SEEN] |
| Size, estimated | ~39.8M installs, ~277K monthly players, $10K+/month [C12 ESTIMATED] | ~43.4M installs, ~173K monthly players, $10K+/month (52% purchases / 48% ads) [C11 ESTIMATED] |
| Age | Released Sep 2015 [C12 REPORTED] | Released 29 Nov 2017 [C1 SEEN] |
| Still alive? | Updated 6 Oct 2026 [C2 SEEN]; installs declining [C12 ESTIMATED] | Updated 12 Nov 2026; live event cards on the Play listing [C1 SEEN] |

- The package names start `br.com.tapps`, a Brazilian namespace. A developer reply gives a support address at `spacesheepgames.com` [C1 SEEN]. Who built it originally is my inference and isn't verified [ESTIMATED].
- A third title, Bid Wars: Auction Master 3D, has 1M+ downloads and was updated 28 Oct 2026 [C7 SEEN].

### Bid Master: Auction Tycoon (Hydodo) — the newcomer that is winning now
- **What:** auctions + appraisal/repair + a pawn shop, plus side businesses (hotel, fishing, restaurant, factory) and **guild wars** against other players [C3 SEEN].
- **Model:** free; ads + purchases from **₹90 to ₹11,800 per item** [C3 SEEN].
- **Size:** 10M+ downloads, 4.2★, 1.01 lakh reviews [C3 SEEN].
  - Estimated ~28.5M installs, ~412K monthly players, **$50K+/month, 81% from purchases** [C13 ESTIMATED].
  - Adding about **201K installs a week** [C13 ESTIMATED].
- **Age:** released **6 Aug 2024** [C3 SEEN]; updated 18 Nov 2026 [C3 SEEN].

### Pawn Shop Master (Lion Studios) — the closest to our core verb, in decline
- **What:** buy from customers by bargaining, spot fakes, sell from shelves [C4 SEEN]. It's a hyper-casual game — very short, simple, ad-funded.
- **Model:** free; ads + purchases from **₹75 to ₹720 per item** [C4 SEEN].
- **Size:** 10M+ downloads, 3.9★, 91.3K reviews [C4 SEEN].
  - Estimated ~15.3M installs, ~106K monthly players, under $10K/month (73% ads) [C14 ESTIMATED].
- **Age:** released 2 Sep 2020 [C4 SEEN].
- **Today:** about 308 installs a week [C14 ESTIMATED]. The newest 3-star review in English on the India store is dated 5 Nov 2025 [C4 SEEN].
- **Publisher:** Lion Studios is AppLovin's publishing arm [C20 REPORTED], and is moving to Tripledot in the sale of AppLovin's games business [C21 REPORTED].

### Dealer's Life series (Abyte Entertainment, Ireland) — the deep haggling sim, paid
- **What:** haggle with customers who have distinct personalities, spot fakes, hire employees, take part in auctions [C5 SEEN]. This is the closest design to IDEA's haggle engine.
- **Model:** paid up front, no ads, no in-app purchases [C5 C6 SEEN].
  - Dealer's Life Pawn Shop Tycoon ₹249 [C6 SEEN]
  - Dealer's Life 2 ₹499, and free with Play Pass [C5 SEEN]
  - Dealer's Life Legend ₹499 [C5 SEEN]
- **Size:**
  - Dealer's Life 2: 500K+ downloads, 4.6★, 9.8K reviews [C5 SEEN]; estimated ~837K installs and ~6.8K monthly players [C15 ESTIMATED].
  - Pawn Shop Tycoon: 100K+ downloads, 4.5★ [C6 SEEN].
  - On Steam, Dealer's Life 2 had sold 150K+ copies by its full launch in Feb 2022 [C22 REPORTED, the studio's own press release].
- **Age:** first game on Android Mar 2017 [C16 REPORTED]; Dealer's Life 2 on Android Feb 2022 [C15 REPORTED]. Last updated 9 Jul 2026, adding "Item Pack #35" [C5 SEEN].

### The non-obvious competitors
1. **"Time pass" — doing something else with the same five minutes.**
   - Ludo King has 1B+ downloads and 1.03 crore reviews [C9 SEEN].
   - Indians use apps about 3.4 hours a day, and short-drama and social apps are taking time from games [C25 REPORTED, Sensor Tower via TechCrunch].
   - The India-store 3-star samples include players who describe these games simply as a way to pass time [C4 SEEN]. That's what this game is up against for the commute slot.
2. **Watching haggling instead of doing it.**
   - Bid Wars 2's own store text leans heavily on reality-TV auction shows [C1 SEEN].
   - One reviewer says they play it because they're a fan of Storage Wars and want to pretend to be a bidder [C1 SEEN — Benjamin M, 8 Jun 2021].
   - The fantasy is fed by TV more than by games.
3. **In India, the auction thrill already has a home: cricket.** "ipl auction game" is the top related search for "auction game" in India [C19 SEEN]. Not our product, but it's where Indian interest in auctions actually sits.
4. **Real bargaining in real markets.** It happens daily, and I have no data on it — mentioned so it isn't forgotten, not as evidence.

Also nearby: **Used Car Dealer Tycoon** (Weplay) — a car-flipping tycoon game with 10M+ downloads, 4.3★, updated Apr 2026 [C8 SEEN].

---

## 2. Proof that people want this

### Where they go today, and how many
- **The biggest living products are Bid Wars 1 and 2.** Together they have about **4.6 million ratings** [C1 C2 SEEN] and an estimated ~83M installs [C11 C12 ESTIMATED]. Both are still updated in Oct–Nov 2026 [C1 C2 SEEN].
- **Reviews this year:**
  - Bid Wars 2 drew 20 English 3-star reviews on the India store between 11 Aug and 8 Nov 2026 [C1 SEEN] — a steady trickle from one star level, in one language.
  - Every one of Bid Master's 20 "most relevant" 3-star reviews is from 2025–2026 [C3 SEEN].
- **The category is growing, not dying.** A 2024 entrant (Bid Master) passed 10M downloads within about two years [C3 SEEN] and is still adding ~200K installs a week [C13 ESTIMATED].
- **Workaround:** TV auction and pawn shows, and IPL auction games (§1).
- **Search interest** (Google Trends, yearly average, relative index 0–100 within each comparison, 2021→2026; 2026 is Jan–Nov) [C18 SEEN]:

  | Term | India | Worldwide |
  |---|---|---|
  | "pawn shop game" | 0 every year | 0–1 |
  | "bid wars" | 0–2 | 0–1 |
  | "auction game" | 2 → 3 → 2 → 4 → 10 → **22** | 2 → 1 → 1 → 1 → 5 → 8 |
  | "supermarket simulator" | 0 → 0 → 0 → **43** → 25 → 23 | 0 → 0 → 0 → 34 → 14 → 10 |

  - **Read straight:** web search is not where this genre lives. Players find these games inside the Play Store.
  - India's rising "auction game" curve is **cricket**: its top related query is "ipl auction game" [C19 SEEN].
  - Shop simulators *do* draw Indian search interest ("supermarket simulator" since 2024).

### Who pays today, and what
- **Price lists on the store** [SEEN]:
  - Bid Wars 2: ₹5–₹9,800 per item [C1]
  - Bid Master: ₹90–₹11,800 per item [C3]
  - Pawn Shop Master: ₹75–₹720 per item [C4]
  - Dealer's Life: ₹249 / ₹499 / ₹499 up front [C5 C6]
- **Revenue estimates** [ESTIMATED, AppGoblin — coarse buckets; treat as order of magnitude only]:
  - Bid Master: $50K+/month, 81% from purchases [C13]
  - Bid Wars 1 and 2: $10K+/month each [C11 C12]
  - Pawn Shop Master: under $10K/month [C14]
- **A team that lives on it:** Abyte Entertainment's Play catalogue is only Dealer's Life games [C5 SEEN]. Dealer's Life 2 sold 150K+ copies on Steam by Feb 2022 [C22 REPORTED].

### People asking for it, in their own words
- **Reddit:** not reachable from here — scripts are refused [C27 SEEN] and the browser tool blocks the site. No thread is cited. **UNVERIFIED** → §9 task 3.
- **In the reviews, players ask for specific changes** [all SEEN]:
  - more items and less repetition — Rosenda Rice, 6 Oct 2020 [C4]
  - different dialogue between characters — Sachin Gorade, 12 Oct 2022 [C5]
  - a manual save — Quantum Deviant, 6 May 2024 [C5]
  - genuinely fair odds — Frank Price, 9 Jul 2026 [C3]
  - a few minutes of play between ads — Sabrina R, 7 Mar 2025 [C1]
  - a way to turn in-game cash into gold — Adrian, 22 Oct 2026 [C1]
- **Indian players are in these games:** one Bid Master reviewer reports a price calculator showing "0 rs" [C3 SEEN — sayali warshe, 23 Jun 2026]. Several Pawn Shop Master reviewers use Indian-English "time pass" phrasing [C4 SEEN; reading them as Indian is my inference, ESTIMATED].

### Reading the signs
**This is PROOF of a market, not a warning.** Several living competitors have tens of millions of installs, are updated this month, and include a 2024 entrant growing fastest. People pay both through purchases and up front.

What is **not** proven: demand in **India specifically**. Indian web-search interest for the genre is near zero [C18 SEEN], and I couldn't get a country split of installs (needs AppMagic, §9).

---

## 3. The 3-star reviews

**Sample** — 99 different people, all SEEN on 2026-11-24 in Play's review dialog (India store, English, phone, 3-star filter):

| Game | 3-star reviews read |
|---|---|
| Pawn Shop Master | 39 (20 "most relevant" + 19 "newest") [C4] |
| Bid Wars 2 | 20 [C1] |
| Dealer's Life 2 | 20 [C5] |
| Bid Master | 20 [C3] |

"Most relevant" tilts toward older, well-voted reviews; Bid Master's are all 2025–26. A person counts once per theme and can appear in several themes.

| Rank | Theme | Pawn Shop Master | Bid Wars 2 | Dealer's Life 2 | Bid Master | **People** |
|---|---|---|---|---|---|---|
| 1 | Too many / too long / forced ads | 16 | 10 | 0 | 10 | **36** |
| 2 | Repetitive, runs out of things to do, late-game grind | 10 | 5 | 9 | 3 | **27** |
| 3 | Bugs, freezes, lost items or progress | 13 | 7 | 4 | 2 | **26** |
| 4 | Pay-or-watch gates, premium-currency walls, long waits | 6 | 4 | 0 | 5 | **15** |
| 5 | Randomness feels rigged or unfair | 0 | 1 | 5 | 7 | **13** |
| 6 | Polish: no or irritating audio, thin animation, typos, poor English | 3 | 1 | 1 | 2 | **7** |
| 7 | Onboarding / UI friction | 0 | 0 | 2 | 3 | **5** |
| — | A paid button appearing where a free one was | 0 | 0 | 0 | 1 | 1 (anecdote) |
| — | No information ("nice", "ok") | 7 | 0 | 1 | 0 | 8 |

### Theme 1 — Ads everywhere (36 people)
- **Pawn Shop Master** [C4]:
  - "In the 30 minutes i played this game i had 62 ads" — Jeremy Van Beek, 7 Oct 2020.
  - An ad after nearly every action — Draycos Dragon, 28 Sep 2020.
  - Ads every few minutes — Inaldina Correia, 6 Jan 2021; 326 found it helpful.
  - The complaint is still arriving in 2023–2025: Alexis Mcgrath, Laura Robertson, and a 31 Mar 2025 review.
- **Bid Wars 2** [C1]:
  - An ad after every bid spoils the mood — Ejul Amri, 27 Oct 2026.
  - Paid to remove ads and still sees them — Boo Boo, 30 Sep 2026.
  - About a third of reward ads don't pay out, sometimes losing the auction haul — Jimmy Burns, 6 Aug 2026.
- **Bid Master** [C3]:
  - Ads now take over a minute, so more watching than playing — Chris W, 31 Oct 2026.
  - Ad length grew from one to three minutes — David James, 20 Sep 2026.
  - The gold-bar ad is always the same loan-app promotion — dean cawdor, 2 Oct 2026.
- **Dealer's Life 2: zero ad complaints.** It's paid and has no ads [C5 SEEN].

### Theme 2 — Repetitive, runs dry, or grinds (27)
- **Pawn Shop Master** [C4]:
  - Every achievement done in under 12 hours, nothing left to aim for — Crystal Hodges, 25 Dec 2020; 321 found it helpful.
  - Items repeat endlessly — Rosenda Rice, 6 Oct 2020.
  - Earned 15M in three days and maxed everything — Harold Smith, 1 Oct 2020.
- **Bid Wars 2** [C1]:
  - Story goals ran out after a week; side quests need items that never drop — Josh Tufts, 9 Oct 2021.
  - Waiting weeks for one item and for new tasks — Tina McGuire, 18 Jan 2026.
- **Dealer's Life 2** [C5]:
  - Same loop from start to finish; fine on Play Pass, poor value at full price — Joey, 21 Apr 2026.
  - Nothing happens after 10–12 hours — Daniel Čelan, 27 Aug 2025.
  - Late game is a slog because item values don't scale with targets — I Penguini, 10 Apr 2022; Uncle Waffles, 12 Jul 2026.
  - Same characters repeat the same lines — Sachin Gorade, 12 Oct 2022.
- **Bid Master** [C3]: wants new levels rather than redesigns — Aimee Kohn, 3 Nov 2026. Several activities feel pointless — Tom France, 15 Jul 2026; K Lelievre, 27 Oct 2026.

### Theme 3 — Bugs and lost progress (26)
- **Pawn Shop Master's** money counter freezes and customers stop coming. The same report recurs over years [C4]:
  - Lilly Greer, 30 Mar 2021
  - Alex Terry, 26 Jun 2023
  - Elizabeth Branham, 13 Jun 2023
  - Kel, 29 Jul 2024
- Progress vanished after closing the app — michael shunk, 17 May 2023 [C4].
- **Dealer's Life 2:** autosave unreliable, wants a manual save — Quantum Deviant, 6 May 2024 [C5]. Cloud saves wiped — Steven G, 26 Aug 2024 [C5].
- **Bid Master:** big wins crash the game and the items vanish — benny dixon, 28 Jun 2026 [C3].
- **Many failures cluster around ad playback:**
  - Pawn Shop Master — Paul Clark, 13 May 2023 [C4]
  - Bid Wars 2 — Slide Scroll, 5 Sep 2020; a Google user, 3 Dec 2019 [C1]
  - Bid Master — sayali warshe, 23 Jun 2026: an auction haul was lost after an ad / network failure [C3]

### Theme 4 — Pay-or-watch gates and premium walls (15)
- **Pawn Shop Master:** a robber takes your cash unless you watch an ad; if no ad loads you're stuck [C4]:
  - Leland Reich Jr, 13 May 2023
  - Tyler Case, 22 Sep 2024
  - Patience Shelley, 1 Oct 2020
- **Bid Wars 2** [C1]:
  - After each auction: accept a loss, watch a video, or spend gold — John Smith, 16 Jan 2022.
  - After ~6 hours, upgrades need bought gold — Adrian, 22 Oct 2026.
- **Bid Master** [C3]:
  - Obviously pay-to-play; gold guarantees what cash only gambles on — Frank Price, 9 Jul 2026.
  - Hours of waiting unless you watch or pay — Michelle Todd, 12 Jul 2026.

### Theme 5 — Randomness that feels rigged (13)
- **Bid Master** [C3]:
  - A stated 40% appraisal chance failing ten times running — Joshua Eddings, 6 Aug 2026.
  - "90%" repairs that keep destroying items — Jay Z, 20 Oct 2026.
  - Paying several times an item's worth just to get it evaluated — Cody Phelps, 6 Sep 2026.
- **Dealer's Life 2** [C5]:
  - A whole in-game week with no buyers, game lost — Nyahcinth, 22 Apr 2024; TuKeen Baker, 26 Sep 2026.
  - Deals feel like a gamble; customers just get their price — John, 29 Jul 2024.
  - Buyers anchor on what you paid — Oleksiy, 4 Dec 2023.
  - Randomness used as fake difficulty — Mark S, 29 Mar 2025.
- **Bid Wars 2:** rivals bid you up until a lot is unprofitable — samuel hyatt, 6 May 2022 [C1]. One review — an anecdote, but directly relevant to IDEA's auction AI.

### Themes 6–7 — Polish and onboarding (12)
- **Polish:**
  - Pawn Shop Master has no audio — J Carlson, 29 Sep 2020; Draycos Dragon [C4].
  - Typos — Bid Wars 2 (Slide Scroll [C1]) and Bid Master (Tom France [C3]).
  - Jarring English — Dealer's Life 2 (Mark S [C5]).
- **Onboarding:**
  - Bid Master locks you into a 15-minute tutorial and you can't even mute the music — Jeffrey Dwyer, 17 Aug 2026 [C3].
  - Dealer's Life 2 has no real tutorial — Travis Taylor, 6 May 2022 [C5].
  - Too many end-of-day screens — reed kaliher, 5 Mar 2024; 68 found it helpful [C5].

---

## 4. Why each top complaint hasn't been fixed — judgement

| # | Complaint | Verdict | Evidence | Can we do better, and what it costs |
|---|---|---|---|---|
| 1 | Ads everywhere | **DELIBERATE** | Ads are most of the money for Pawn Shop Master and Bid Wars 1 (73%) and half for Bid Wars 2 [C11 C12 C14 ESTIMATED]. The same complaint runs 2020→2026, answered with template replies [C1 C4 SEEN]. Bid Master's ads got *longer* in 2026 [C3 SEEN]. The one ad-free series charges up front [C5 SEEN] | Yes — IDEA already has ads-light, rewarded-only, opt-in. **It costs money:** fewer ads means less revenue per player, so retention has to make up for it. This is our wedge *because* it's deliberate for them; they won't close it |
| 2 | Runs out / repetitive / grind | **HARD**, partly SEGMENT | Pawn Shop Master is hyper-casual, published by a UA-driven publisher [C20 REPORTED] and built for short lifetimes: SEGMENT. Bid Wars 2 runs live events [C1 SEEN] and players still run dry. Dealer's Life 2's small studio ships content packs ("#35") [C5 SEEN], yet players exhaust it in 10–16 hours | **Just as hard for us.** It's the genre's core problem — the "solved loop" in IDEA §3. Item variants, characters who remember you, and personalities that each punish a different habit help, but don't solve it. Budget for it |
| 3 | Bugs and lost progress | **CAN'T TELL** overall. Some is **HARD** (ad SDK interruptions break game state — several reviews tie losses to ads); some looks **MISSED** (Pawn Shop Master's freeze reported 2021, 2023, 2024 while updates only say "Bug Fixes" [C4 SEEN]) | — | Partly avoidable: fewer ads means fewer ad-induced breaks, and IDEA already saves every round with atomic writes. Still routine engineering risk |
| 4 | Pay-or-watch gates, premium walls | **DELIBERATE** | Each gate is a monetisation point: the robber ad [C4], the after-auction profit menu [C1], guaranteed success only for gold [C3] (all SEEN) | Yes — IDEA has no gates and a never-ends floor. Same cost as #1 |
| 5 | Randomness feels rigged | Bid Master: **DELIBERATE** — failure rates drive gold and ad spend, and "100% with gold" is the tell [C3 SEEN]. Dealer's Life 2: **HARD** — randomness supplies difficulty in a game with nothing to sell; a 2022 balance patch [C5 SEEN dev reply] didn't end the complaints. Bid Wars 2's rival AI: **CAN'T TELL** (one review) | — | Yes for us: no %-chance gates. Shown odds must be the real odds — or better, deterministic outcomes plus IDEA's post-deal verdict and rival-estimate reveal. Dealer's Life 2 shows the *design* side is HARD: remove randomness and the game gets easy and solvable. Same tension as #2 |
| 6 | Polish (audio, typos, English) | Probably **MISSED** — low confidence | Cheap fixes left for years, e.g. Pawn Shop Master's missing audio reported in 2020 [C4] | Easy. Basic sound effects + a mute in Settings from minute one |
| 7 | Onboarding | Bid Master: **MISSED** — the developer called the locked tutorial "a fair call" in Aug 2026 [C3 SEEN]; I can't see whether it's fixed. Dealer's Life 2: **MISSED** — help exists behind a "?" nobody finds [C5 SEEN dev reply] | — | Easy: IDEA's one-line hints, Settings reachable at any time |

---

## 5. How they get users

- **Paid acquisition** — all the free ones:
  - Attribution SDKs (used to measure paid installs) are present: Singular in Bid Wars 1 and 2 [C11 C12 REPORTED]; Adjust in Pawn Shop Master and Bid Master [C13 C14 REPORTED].
  - Lion Studios exists to publish games through "cost-effective user acquisition" [C20 REPORTED].
  - Bid Master's ~200K installs a week [C13 ESTIMATED] fits large paid campaigns [ESTIMATED, my inference].
- **The ad networks are both their revenue and their user source.** The same networks — AppLovin, Unity, AdMob, Fyber — serve their ads and sell them installs [C11–C14 REPORTED].
- **Play Store features:**
  - Live "Events and offers" cards on the listing — Bid Wars 2 and Bid Master [C1 C3 SEEN].
  - Google Play Games on PC — Pawn Shop Master, Bid Master [C3 C4 SEEN].
  - **Play Pass** — Dealer's Life 2 [C5 SEEN].
- **Keyword riding:** Bid Wars 2's description repeats reality-TV and storage-auction phrases [C1 SEEN].
- **Premium and PC:** Dealer's Life sells on Steam (150K+ copies of DL2 [C22 REPORTED]) and on Play at ₹249–₹499.
- **Same channel for everyone:** paid installs bought from ad networks, plus Play search.
  - For us, with no budget, that channel is **closed**.
  - The blind spot: none of them is set in India, speaks Tanglish or Hinglish, or targets Indian-language store searches. [ESTIMATED from listings seen; I didn't audit their social channels]
  - Our open channels are Play search in Indian phrasing, WhatsApp-shareable deal cards, Indian creators, and Play Pass later (Dealer's Life shows Google accepts haggling games there).

---

## 6. Who tried this and died

| Who | What happened | Visible reason |
|---|---|---|
| **"Pawn Shop" — YALP GAMES LLC** (`com.game.pawnshop`) | Released 12 Dec 2024, last updated 18 Dec 2024 [C17 REPORTED]; the Play page returns **404** [C10 SEEN] | **Nobody heard of it** — AppGoblin has no install or rating figures [C17]. Updated once, then gone. Can't tell if pulled or removed |
| **Pawn Stars: The Game** (A&E Television Networks Mobile) | Reportedly removed from both app stores [C24 REPORTED — a forum answer, **UNVERIFIED**] | Licensed TV brand; likely a licensing or business decision. Can't tell |
| **Pawn Shop Master** — fading, not dead | ~308 installs a week, newest English 3-star review on the India store from Nov 2025, updates only say "Bug Fixes" [C4 SEEN, C14 ESTIMATED]; publisher being sold [C21 REPORTED] | The hyper-casual model: millions of installs, then a long fade within ~2 years. Its 10M+ downloads measure its **marketing**, not lasting demand |
| **Bid Wars 1** — fading, not dead | Still updated Oct 2026 [C2 SEEN]; installs declining [C12 ESTIMATED] | Replaced by its own sequel |
| **Dealer's Life (original) on Steam** | Owner estimate 0–20K vs 150K+ for the sequel [C23 REPORTED] | Not dead; the sequel took over |

**Lessons from the graveyard:**
- The deaths visible here are **small clones nobody heard of** and **licensed TV tie-ins**.
- The ad-every-action haggle game (Pawn Shop Master) burned bright and faded in about two years.
- The survivors over a decade (Bid Wars) run live updates and buy users. The quiet survivor (Dealer's Life) charges up front and serves a loyal niche.
- **Limit:** AppGoblin's and AndroidRank's search pages are off-limits to scripts, so I couldn't list removed apps systematically. This list is incomplete.

---

## 7. The parity trap — judgement

What players will expect on day one *because the competitors have it*, and whether v1 can refuse it.

| Expected because… | v1 (IDEA §8) | Refuse it and survive? |
|---|---|---|
| Auctions against rivals (Bid Wars, Bid Master, Dealer's Life 2) | Auction-lite ✓ | **Must have** — it's in the pitch |
| Fakes to spot (Pawn Shop Master, Dealer's Life) | ✓ | **Must have** |
| Shop upgrades / growth (all) | Stall → shop → showroom goal ✓ | **Must have** |
| Offline play (all list it [C1 C3 C4 SEEN]) | ✓ | **Must have** |
| Sound — its absence is a complaint (Pawn Shop Master) | Not explicit in IDEA | **Must have — add** basic sound effects and an early mute |
| Saves that never lose progress (complaints everywhere) | ✓ (every round, backups) | **Must have** |
| Restoration / repair / appraisal timers (Bid Wars 2, Bid Master, Dealer's Life 2) | Out | **Refuse in v1.** Pawn Shop Master hit 10M without it; Bid Master's %-based repairs are a *complaint* source |
| Employees / assistants (Dealer's Life 2) | Out | **Refuse in v1** |
| Live events and limited-time offers (Bid Wars 2, Bid Master) | Out | **Refuse.** Can't be sustained solo or offline. Dealer's Life 2 has none and holds 4.6★ [C5 SEEN] |
| Many cities / districts (Bid Wars 2, Bid Master) | Coimbatore only | **Refuse in v1** |
| Business-empire side-games: hotels, fishing, city building (Bid Wars 2, Bid Master) | Out | **Refuse entirely.** Reviewers call them pointless [C3 SEEN] |
| Guilds / multiplayer (Bid Master) | Out | **Refuse entirely** (IDEA non-goal) |
| Daily rewards (Bid Wars 2) | Today's special, no streak ✓ | Enough |
| Cloud save via Play Games (Dealer's Life 2) | Auto Backup only | **Refuse in v1.** Even Dealer's Life 2's cloud save drew a wiped-saves complaint |
| Ad-removal purchase (Pawn Shop Master, Bid Wars 2) | No ads in v1 | Not needed |

**The parity v1 can't skip is small:** auctions, fakes, growth, offline, sound, reliable saves. Everything the free giants pile on top is exactly what generates their 3-star complaints. Dealer's Life is the proof that a narrow, haggle-focused game can satisfy without it.

---

## 8. The honest verdict

**Is there a market? Yes — proven, but global, and unproven in India.**
- Two Bid Wars games have ~4.6M ratings and are still updated this month [C1 C2 SEEN].
- A 2024 entrant reached 10M+ and is the fastest-growing [C3 SEEN, C13 ESTIMATED].
- People pay through purchases up to ₹11,800 and up front at ₹249–₹499 [C1 C3 C5 C6 SEEN].
- A studio has lived on haggling games since 2017 [C5 SEEN, C22 REPORTED].
- **India specifically is unproven.** Indian players appear in the reviews [C3 C4 SEEN], but Indian web search for the genre is ~0 [C18 SEEN] and I have no country split (§9).

**Is there a gap? A real one, but thin — lead with that.**
- **Real:** the free market leaders deliberately farm ads (36 of 99), gate progress (15) and use odds that feel rigged (13). Those are their business models (§4), so they won't fix them. A fair, ad-light, readable haggling game is a gap the incumbents *can't* close without hurting their revenue.
- **Thin, because:**
  - The "deep, fair, no-ads haggling" corner is already served — by a paid series rated 4.5–4.9★ [C5 C6 SEEN].
  - Our angle versus Dealer's Life is: **free, Indian setting and voices, readable fairness, and variety**. Whether the Indian setting pulls players is **untested**. The one India data point shows Indians love auctions as a *cricket* mechanic [C19 SEEN], not as a pawn-shop mechanic.
  - The deepest complaint after ads — the game runs dry (27) — is **HARD** for everyone, including us.
- **What this means for v1:** the gates in IDEA §7 are the right test.
  - G1 checks whether our haggle avoids becoming "repetitive".
  - G2 checks whether players return without ads or gates pulling them.
  - G3 checks whether free channels can deliver installs at all — the channel the competitors buy and we can't.

---

## 9. What only you can check

Each one needs your login, your phone, or a site my tools can't reach.

1. **AppMagic** (free account, your login): the **India share** of installs and revenue for Bid Wars 1 and 2 and Bid Master. This answers "do Indians play these?"
2. **Play Store on your phone:** search "auction game", "pawn shop", "bargain game" and "mol bhav game". Note the top 10 results and whether any is set in India. Store search can't be scripted under Play's rules.
3. **Reddit**, in your own browser: "haggling game", "Dealer's Life alternative", and India-set game requests in r/IndianGaming. Bring back 3–5 thread links.
4. **YouTube:** search "Bid Wars gameplay Hindi" and "Bid Master Tamil". Do Indian creators cover the genre, and with what view counts?

---

## 10. Sources (all seen or retrieved 2026-11-24 unless noted)

| ID | Source | Label |
|---|---|---|
| C1 | [Bid Wars 2 on Play — India store; 3-star review dialog; About dialog (in-app price range, release date)](https://play.google.com/store/apps/details?id=br.com.tapps.bidwars2&hl=en_IN&gl=IN) | SEEN |
| C2 | [Bid Wars 1 on Play](https://play.google.com/store/apps/details?id=br.com.tapps.bidwars&hl=en_IN&gl=IN) | SEEN |
| C3 | [Bid Master on Play; 3-star review dialog; About dialog](https://play.google.com/store/apps/details?id=com.bid.master.war.auction.battle&hl=en_IN&gl=IN) | SEEN |
| C4 | [Pawn Shop Master on Play; 3-star "most relevant" and "newest"; About dialog](https://play.google.com/store/apps/details?id=com.alphapotato.pawnshopmaster&hl=en_IN&gl=IN) | SEEN |
| C5 | [Dealer's Life 2 on Play; 3-star review dialog](https://play.google.com/store/apps/details?id=com.AbyteEntertainment.DealersLife2&hl=en_IN&gl=IN) | SEEN |
| C6 | [Dealer's Life Pawn Shop Tycoon on Play](https://play.google.com/store/apps/details?id=com.AbyteEntertainment.DealersLifeFull&hl=en_IN&gl=IN) | SEEN |
| C7 | [Bid Wars: Auction Master 3D on Play](https://play.google.com/store/apps/details?id=com.byaliens.bidwars4&hl=en_IN&gl=IN) | SEEN |
| C8 | [Used Car Dealer Tycoon on Play](https://play.google.com/store/apps/details?id=com.dragonfly.cardealer&hl=en_IN&gl=IN) | SEEN |
| C9 | [Ludo King on Play](https://play.google.com/store/apps/details?id=com.ludo.king&hl=en_IN&gl=IN) | SEEN |
| C10 | [Pawn Shop (YALP) on Play — returns HTTP 404](https://play.google.com/store/apps/details?id=com.game.pawnshop&hl=en_IN&gl=IN) | SEEN |
| C11 | [AppGoblin — Bid Wars 2](https://appgoblin.info/apps/br.com.tapps.bidwars2) | installs, players, revenue: ESTIMATED · dates, SDKs: REPORTED |
| C12 | [AppGoblin — Bid Wars 1](https://appgoblin.info/apps/br.com.tapps.bidwars) | as C11 |
| C13 | [AppGoblin — Bid Master](https://appgoblin.info/apps/com.bid.master.war.auction.battle) | as C11 |
| C14 | [AppGoblin — Pawn Shop Master](https://appgoblin.info/apps/com.alphapotato.pawnshopmaster) (2026-11-23) | as C11 |
| C15 | [AppGoblin — Dealer's Life 2](https://appgoblin.info/apps/com.AbyteEntertainment.DealersLife2) | as C11 |
| C16 | [AppGoblin — Dealer's Life Pawn Shop Tycoon](https://appgoblin.info/apps/com.AbyteEntertainment.DealersLifeFull) (2026-11-23) | as C11 |
| C17 | [AppGoblin — Pawn Shop (YALP)](https://appgoblin.info/apps/com.game.pawnshop) (2026-11-23) | REPORTED |
| C18 | [Google Trends — India and worldwide, 5 years](https://trends.google.com/trends/explore?date=today%205-y&geo=IN&q=bid%20wars,pawn%20shop%20game,auction%20game,supermarket%20simulator) — values read from the Trends page's own data in the browser | SEEN |
| C19 | Google Trends — related queries for "auction game", India, 5 years (same page) | SEEN |
| C20 | [PocketGamer.biz — AppLovin launches publishing division Lion Studios](https://www.pocketgamer.biz/applovin-launches-publishing-division-lion-studios) | REPORTED |
| C21 | [PocketGamer.biz — Tripledot to acquire AppLovin's games business](https://www.pocketgamer.biz/tripledot-to-acquire-applovins-games-business-6291588) | REPORTED |
| C22 | [Game Developer — "Dealer's Life 2 now available" press release (150K+ copies)](https://gamedeveloper.com/press-release/dealers-life-2-now-available) · [100K in Early Access](https://gamespress.westeu-v2.propressroom.com/de/Dealers-Life-2-sells-100k-Copies-during-Steam-Early-Access) | REPORTED (the studio's own release) |
| C23 | [SteamSpy — Abyte Entertainment](https://www.steamspy.com/dev/Abyte+Entertainment) (via a search summary; not opened) | REPORTED |
| C24 | [itch.io forum post on Pawn Stars: The Game removal](https://itch.io/post/9898668) (via a search summary; not opened) | REPORTED — **UNVERIFIED** |
| C25 | [TechCrunch — India app downloads 25.5B in 2025 (Sensor Tower)](https://techcrunch.com/2026/01/21/indias-app-downloads-rebounded-to-25-5-billion-in-2025-fueled-by-ai-assistants-and-microdrama-boom/) | REPORTED |
| C27 | [reddit.com/robots.txt](https://www.reddit.com/robots.txt) returns a block notice to scripts (2026-11-23); the browser tool refused reddit.com (2026-11-24) | SEEN |
