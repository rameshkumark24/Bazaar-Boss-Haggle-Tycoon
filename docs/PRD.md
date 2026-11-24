# Bazaar Boss: Haggle Tycoon — Product Requirements

> **The source of truth since 2026-11-24.** Read this file before touching code.
> - [IDEA.md](IDEA.md), [RESEARCH.md](RESEARCH.md) and [COMPETITORS.md](COMPETITORS.md) are the background it was built from. Where they disagree with this file, this file wins.
> - To change the product, change this file first and add a line to the [change log](#change-log). Then change the code.
> - How the game is built (engine, file formats, code) belongs in the TRD, not here.
>
> **Where things stand (2026-11-24):**
> - No game code exists yet.
> - The Business verdict says **test first**: a one-week waitlist test decides whether v1 is built at all (§12.1). Before starting the build, check that §12.1 records the test's result.
> - The waitlist page is built ([site/README.md](../site/README.md)). It waits for the owner's Google Form link and contact email.
>
> **Labels:**
> - Unmarked means decided — the plan of record.
> - **[A]** means an assumption: it stands until a test or the owner changes it. Every tuning number carries one.
> - **[Qn]** means open; see §10.
>
> **Names and IDs:**
> - "The owner" is the solo developer who makes the decisions.
> - F, DR and CR numbers belong to this file.
> - H-numbers (H1-1 … H5-13) point to IDEA.md → Hardening decisions. L-numbers point to RESEARCH.md.

---

## 1. What this is

Bazaar Boss: Haggle Tycoon is a free, single-player, offline Android game set in an Indian bazaar. You buy stock at kabadi auctions and from walk-in sellers, spot the fakes, and sell by haggling one-on-one with customers who hide their walk-away price but show their mood, growing a footpath stall into a shop. It's for Indian phone gamers aged 18 and over who love a good bargain, with characters who talk the way bazaars talk — in English, Tanglish or Hinglish — on Google Play in India first. **"It's a free haggling game that never makes you watch an ad to keep playing — you win by bargaining, not by paying or waiting."**

> **Positioning:** for Indian phone gamers who love a good bargain but are tired of auction games that stop every few minutes for an ad, Bazaar Boss is the bazaar haggling game that never makes you watch an ad to keep playing, unlike Bid Wars and Bid Master.

**Core feeling:** the thrill of a lot that turns out better than it looked, and the satisfaction of a hard bargain won.

---

## 2. The user

**Who they are**
- Indian Android phone gamers, 18 and over (the declared audience).
- They already play casual auction, pawn-shop or tycoon games in short sessions.
- They're at home in code-mixed Tamil–English or Hindi–English.
- Many play on 2–3 GB RAM phones with little free storage.
- [A] The genre is proven worldwide, with tens of millions of installs. Demand in India specifically is not proven (COMPETITORS §2, §8).

**The moment of need**
- A spare few minutes — a commute, a queue, a tea break — when they want a small win that feels earned.
- In the games they play now, that moment gets interrupted:
  - an ad after every bid
  - a robber who takes your cash unless you watch one
  - a repair that fails unless you pay gold

**What they do today instead**
- **Play Bid Wars 1 or 2, or Bid Master** (10M+ downloads each) and sit through forced ads and pay-or-watch gates. A sample of 99 people's 3-star reviews across four genre games names:

  | Complaint | People |
  |---|---|
  | Ads | 36 |
  | Repetitive play | 27 |
  | Bugs and lost progress | 26 |
  | Pay-or-watch gates | 15 |
  | Odds that feel rigged | 13 |

- **Pay ₹249–₹499 up front for Dealer's Life.** It has no ads and deep haggling, but it's English-only and nothing about it is Indian.
- **Watch TV auction and pawn shows, or play IPL auction games.** In India, the auction thrill lives in cricket.
- **Fill the same five minutes with something else** — Ludo King, or scrolling.
- **Bargain for real**, in real markets.

**What would make them try it** [A — unverified until G2 and G3]
- They won't quit their current game. The aim is to be the second game they keep.
- That means:
  - a small download
  - offline play
  - no account
  - a first haggle within 30 seconds
  - no ad toll

---

## 3. The core loop

**The repeated action is the haggle:** name a price, read the face, adjust, then close or walk away.

One in-game **day** is the session. It takes about 3–5 minutes [A]:

1. **Morning — get stock.**
   - Go to today's kabadi auction: a 10-second peek, then bid against 2 rivals, then watch the gunny bag empty item by item. Keep or scrap each item.
   - Or skip the auction and deal with walk-in sellers.
2. **Look closer.** Inspect anything doubtful. Clues (weight, marks, wear) show its condition and give fakes away.
3. **Serve 5–8 customers** [A], one at a time; you choose whom to serve next. Each one is a haggle:
   1. They bring an item to sell, or want one from your display.
   2. They name a price. You see their face, their mood word and their patience.
   3. You answer: slide to a price, tap −10%, −25% or "Last price?", or walk away.
   4. They counter. Their mood and patience move, and one line says why.
   5. Repeat until there's a deal or someone walks away.
   6. After a deal you see your profit, any reputation change and the verdict ("He'd have gone to ₹380"). Share the deal if it's a good one.
4. **Evening — kanakku.** The best deal comes first, then the day's profit, your reputation and tomorrow's teaser. Upgrade if the goal is reached.
5. **Profit → next goal → next day.** The v1 arc is stall → shop in 30–45 minutes of play [A]; the showroom shows as the next goal.

```
customer arrives → offer ⇄ counter (mood, patience move) → deal or walk → verdict line → next customer
      ↑                                                                                   │
stock: auction (peek → bid → reveal) · walk-in sellers  ←──────  profit → next goal → upgrade
```

**What stops the loop from being solved:** each personality punishes a different habit (F3). The simulator's bots must show that no single habit wins (§7.2).

**Why players come back** (v1):
- the next-goal bar, which starts at 20%
- tomorrow's teaser
- Today's special
- 2–3 regulars who remember you
- the found counter
- the deal card
- a fresh start for anyone returning

None of these punishes absence.

---

## 4. Features — v1

**What v1 is:** IDEA §8, "The first version", as written. The Business verdict kept that scope and sized it at 17 build slices — about 16 weeks to the first public player at ~5 hours a week [A].

**Its goal:** one real player completes buy → haggle → profit → grow, and the owner learns whether people want more haggles.

**Where it goes:** to the 14-tester closed test, then — if G2 passes — to production in India.

**What it lacks:** v1 has no ads, no purchases and no internet access.

Each feature lists four things:
- **Does** — what it does.
- **Acceptance** — criteria you can check by hand on a phone.
- **When it fails** — what the player sees.
- **Does not** — what it explicitly doesn't do.

### F1 · First launch and the voice picker
**Does:**
- The first screen offers three voices — English, Tanglish, Hinglish — each with a sample line.
- One tap and the stall opens, with a walk-in seller already waiting.
- Hints are one line each. There's no tutorial to sit through.

**Acceptance**
1. Given a fresh install, when I open the game, then the first screen is the voice picker: three voices, each with a sample line, and a mute control. Nothing asks for a name, sign-in, email, age or permission, and no ad appears.
2. Given I tap a voice, then the stall opens with a walk-in seller at the counter and a one-line hint.
3. Given a fresh install and a stopwatch started at the icon tap, when I play normally, then I make my first offer within 30 seconds.
4. Given my first 60 seconds of play, then I have closed a first deal and seen its verdict line, and no ad, sign-in or permission prompt has appeared.
5. Given I chose a voice, when I relaunch, then the voice picker doesn't appear again; it returns only after Start over. I change voice in Settings.

**When it fails:** a line missing in the chosen voice shows the English line — never a blank or a code. Release builds can't contain a missing line (F13), so this is a last resort.

**Does not:**
- ask anything about the player
- play an intro video
- lock the player in a tutorial
- show the age screen, which arrives with ads at the first ad tap

### F2 · The haggle
**Does:**
- One negotiation with one customer over one item, in both directions: customers who buy from you, and walk-in sellers who sell to you.
- The player sets a price with a slider or the quick buttons (−10%, −25%, "Last price?"), or walks away.
- The customer:
  - hides a walk-away price
  - shows their mood as a face plus a word
  - loses patience each round
- Every round says what changed and why.
- After a deal, a verdict line gives the exact price they'd have gone to.

**Acceptance**
1. Given a haggle, when I make an offer, then the customer counters, accepts or walks away, and one line explains their mood ("Annoyed — you barely moved").
2. Given any round, then I see:
   - the face, the mood word and patience
   - the item and the price on the table
   - the offers so far, each with its reason

   I never see the walk-away price, and mood is never shown by colour alone.
3. Given I'm buying, when I move the slider, then it can't go above my cash.
4. Given patience runs out, when my offer still misses their walk-away price, then they walk away. When my offer already meets it, they accept.
5. Given a deal, then the result shows:
   - my profit or cost
   - any reputation change, with its reason
   - the verdict line — "He'd have gone to ₹380" when selling, "She'd have taken ₹220" when buying
6. Given I tap "Last price?", then the customer names a firm price I can accept with one tap, and the button greys out for the rest of this haggle [A].
7. Given I tap Walk away, then the haggle ends. The customer may call me back once with a better price ("Okay okay, vaanga!" / "Achha theek hai, aao!"). If they don't, they're gone for the day.
8. Given a haggle ends in a walk-away (mine or theirs), then a "Lesson: …" line says what went wrong, without the walk-away price [A].
9. Given each of the five personalities in the haggle lab, then each reacts visibly to the habit it punishes (F3).

**When it fails:** if a call comes in, or Android kills the game mid-haggle, reopening shows the same customer, item, offers, mood and patience. A deal is never lost and never applied twice.

**Does not:**
- reveal the walk-away price before a deal is done
- move a customer's walk-away price in reaction to your offers
- let anyone close a deal past their walk-away price
- use a real-time clock — patience counts rounds, not seconds [A]
- let the player type a price

### F3 · Customers: personalities, types and regulars
**Does:**
- 8 customer types are built on 5 personalities, and each personality punishes a different bad habit:

  | Personality | Punishes |
  |---|---|
  | Tough bargainer | creeping up in small steps |
  | Emotional seller | insulting lowballs |
  | Rushed buyer | slow, many-round haggles |
  | Collector | lowballing them on things they love — costs reputation |
  | Con artist | skipping inspection — you buy a fake |

- 2–3 regulars come back and remember your last deal.
- Women and men appear as both traders and customers.

**Acceptance**
1. Given the haggle simulator runs its five bots (lowballer, creeper, splitter, impatient, honest) over many days, then no bot earns the most against all five personalities: every habit loses somewhere.
2. Given a regular I've dealt with before, when they come back, then their opening line refers to our last deal ("You again! That 'brass' lamp was painted tin!").
3. Given one in-game week of play, then I've met all five personalities, and women as well as men [A].
4. Given every customer's look and lines, then nothing ties a personality to a region, religion, caste or community. The validator checks words; a human read checks the rest.

**When it fails:** a line that reads wrong or offends → long-press it → Report this line (F19).

**Does not:**
- use real people or celebrities
- let any customer know what you paid (§7.2)
- have more than 2–3 regulars in v1
- punish the player for being away

### F4 · Items, inspection and fakes
**Does:**
- 40 items in about 6 categories [A — category count].
- Each item has:
  - a category
  - a base value
  - a condition
  - a rarity
  - a fake chance
  - 2–3 clues (weight, marks, wear)
- Inspect shows the clues and the condition, so the player can judge value and spot fakes.
- A "Found X of 40" counter tracks the items seen.

**Acceptance**
1. Given any item I hold or am offered, when I inspect it, then I see its condition and its clues, in words.
2. Given an item that is a fake, then at least one of its clues gives it away to a player who inspects.
3. Given the 40 items, then each has all six properties and 2–3 clues. None is a real brand or design, coin, stamp, banknote, idol, temple artefact or film poster.
4. Given I see a kind of item for the first time, then "Found X of 40" goes up by one — once.

**When it fails:** an item described wrongly → Report this line.

**Does not:**
- tell the player "this is fake" without an inspection
- turn a fake genuine (or the reverse) after the item exists
- use a percentage chance for any appraisal or repair
- describe real antiques or real prices

What inspecting costs, and how sure a clue is, are still open (Q4).

### F5 · The kabadi auction (lite) and the lot reveal
**Does:**
- One auction each in-game morning.
- A 10-second peek at part of the lot, then live bidding against 2 rival dealers. Each rival has a budget, a risk appetite and favourite categories.
- The winner pays. The gunny bag is emptied item by item, and the player keeps or scraps each one.
- After every auction, each rival's estimate of the lot is shown.

**Acceptance**
1. Given the morning, when I open the auction, then I see part of the lot for 10 seconds, then bidding opens. I may skip the auction instead [A].
2. Given live bidding, when anyone bids, then I see the new price and who bid, and the countdown starts again [A].
3. Given my cash, then I can't bid above it, and a rival never bids above its budget.
4. Given the hammer falls, then the result names the winner and the price, and shows every rival's estimate of the lot.
5. Given I won, then my cash goes down by the price once, and the bag empties one item at a time.
6. Given the reveal, when I scrap items, then one confirmation lists them with the total ("Scrap 3 items for ₹120?"), and they're sold at once.
7. Given I lost or skipped, then my cash is unchanged and walk-in sellers come instead.
8. Given the game goes to the background during bidding, when I come back, then the auction is paused where it was: nobody bid while I was away.
9. Given the reveal's look and sound, then nothing resembles a slot machine — no reels, spins, flashing jackpots or coin showers.

**When it fails:**
- If the game dies during the reveal, the items already shown are in the godown and the rest are revealed on return.
- With too little cash to bid, the peek still plays and the bid button says "Not enough cash".

**Does not:**
- let rivals see more than the peek
- change a lot after it's made
- tie a lot to anything the player spent or watched
- hold more than one auction a day, or have more than 2 rivals, in v1
- have rivals bluff
- run godown-clearance auctions

### F6 · The market day and the stall
**Does:**
- One in-game market day is the session: morning stock (auction or walk-in sellers) → 5–8 customers → evening kanakku [A for the numbers].
- The stall is home. It shows:
  - cash
  - reputation
  - the day number
  - display slots
  - the next-goal bar
  - Today's special
  - a mute button

  Everything else is one tap away.
- In-game time moves only when the player acts.

**Acceptance**
1. Given the stall, then it shows cash (Indian grouping, coin icon), reputation, the day number, display slots, the next-goal bar, Today's special and mute.
2. Given an item in the godown, when I put it on display, then buyers can ask for it, and I can take it back off [A — buyers ask for what's on display].
3. Given more than one customer is waiting, then I choose whom to serve. Anyone not served by the end of the day leaves quietly, with no reputation change [A].
4. Given a calendar day, then the stall has one Today's special — a special lot or visitor — whether or not I played yesterday. It can't be claimed twice that day.
5. Given I close the game mid-day and come back days later, then nothing has changed: no timers, no decay, no lost stock and no guilt message.
6. Given I come back after a gap [A — the gap's length is a tuning number, Q9], then a "Welcome back — here's a cheap starter lot" offer appears, once.
7. Given the day's last customer has gone, then the day ends with the kanakku (F10).

**When it fails:** if the phone's date has gone back by 1–2 days, no new Today's special appears until the date passes the last one seen. A jump back of more than 2 days is treated as a clock correction, and the special resets to today (DR26).

**Does not:** streaks, daily-login rewards, penalties for missed days, energy, real-time timers, or notifications in v1.

### F7 · Growth: the next goal, stall → shop, and the tier-up
**Does:**
- One next goal is always visible, with a bar ("Shop: ₹18,400 / ₹25,000").
- **The bar counts profit earned since the last tier,** so it only ever rises. Buying stock never moves it backwards. *(Q6, decided 2026-11-24.)*
- The first bar starts at 20%: Periyappa's gift is a head start on the bar, on top of the starting cash.
- The v1 arc is stall → shop in 30–45 minutes of play [A], with the showroom shown as the next goal.
- When the bar is full, the upgrade is offered at day end. It **costs no cash** — it's earned, not bought — and it's celebrated once.

**Acceptance**
1. Given a new game, then the stall shows the bar for the shop, already 20% full and labelled as Periyappa's gift.
2. Given I spend cash on stock, then the bar doesn't move. It rises only when a sale makes a profit.
3. Given the bar is full at day end, when I tap Upgrade, then the stall becomes a shop — no cash is taken — and the tier-up moment plays once.
4. Given I have the shop, then the next goal shows the showroom.
5. Given the closed test, then the median play time to the shop is 30–45 minutes, within ±25% of the simulator's prediction (Check 3, §12.2).

**When it fails:** if the game dies during an upgrade, the upgrade is either done (shop shown) or not done — never applied twice.

**Does not:** sell progress; move the player down a tier; put a deadline on a goal; let spending lower the bar.

Still open: what happens in v1 when the showroom bar fills (Q7).

### F8 · Reputation
**Does:**
- One reputation meter.
- Bad faith lowers it: lowballing a collector on something they love, or selling a known fake at a genuine price. Fair dealing raises it [A].
- Reputation changes how future customers treat you — how patient they start, and how far from their walk-away price they open [A — tuned in the haggle lab].

**Acceptance**
1. Given a deal that changes reputation, then the result says by how much and why, in one line ("−3 reputation — lowballed a collector").
2. Given I lowball a collector on an item they love, then reputation drops.
3. Given I sell an item my inspection showed to be fake, at a genuine price, then reputation drops.
4. Given my reputation is at its lowest, then customers still come, and fair deals raise it again.
5. Given a reputation change, then it applies from the next customer, never to the haggle in progress.

**When it fails:** nothing to fail.

**Does not:** block play; punish absence; get bought or sold.

Still open: selling a fake you didn't know was fake (Q5).

### F9 · The godown
**Does:**
- Lists everything the player holds, with filter and sort.
- Shows the "Found X of 40" counter.
- Has two different empty states.

**Acceptance**
1. Given items, then I can filter by category and sort by what I paid, by condition, or newest first [A].
2. Given I've never had an item, then it says "Your godown is empty. Buy at the morning auction or from walk-in sellers."
3. Given a filter that hides everything, then it says "Nothing matches this filter" with a Clear button.
4. Given any item, then I can inspect it, put it on display, or scrap it from here.

**When it fails:** nothing to fail.

**Does not:** sell anything by itself; hide items; offer a paid storage upgrade.

### F10 · Day end — the kanakku
**Does:**
- The evening screen. In order, it shows:
  - the day's best deal
  - the day's profit
  - reputation
  - today's deals
  - the best deal ever
  - tomorrow's teaser
- It offers the upgrade when the goal is reached.
- It is one screen.

**Acceptance**
1. Given a day with deals, then the first thing shown is the day's best deal ("bought ₹X → sold ₹Y").
2. Given a day with a loss, then the loss is shown with a "Lesson: …" line, and the screen still ends on tomorrow's teaser.
3. Given a teaser ("A collector wants a brass lamp tomorrow"), then what it names happens the next in-game day.
4. Given the goal is reached, then the Upgrade button is here.
5. Given any day, then the day end is a single screen [A] — not a chain of end-of-day screens, which is a Dealer's Life 2 complaint.

**With no data:**
- No deals today: "No deals today", still followed by tomorrow's teaser.
- No best deal ever yet: "Your first deal will show here."

**When it fails:** nothing to fail.

**Does not:** show an ad, ever — including after ads arrive; blame the player.

### F11 · The floor — the game never ends
**Does:**
- A bad bid hurts but never ends the game.
- Scrap always sells, and cheap walk-in sellers always come.
- When the player is broke, the low-cash overlay offers three ways out:
  - Periyappa's small, interest-free loan, repaid automatically from sales
  - scrapping items
  - a cheap-seller day

**Acceptance**
1. Given I can't afford anything on offer and have nothing worth selling [A — "broke" is defined in tuning, Q9], then the low-cash overlay appears with the three ways out.
2. Given I take the loan, then:
   - the cash arrives at once
   - a share of each later sale repays it [A — the share is a tuning number]
   - there's no interest, no deadline and no penalty
   - only one loan can be open at a time [A]
3. Given a cheap-seller day, then walk-in sellers bring items I can afford.
4. Given the simulator plays thousands of days with every bot, then no game reaches a dead end — no cash, no sellable stock, no loan and no affordable seller all at once.

**When it fails:** nothing to fail.

**Does not:**
- end the game
- charge interest
- nag
- offer an ad or a purchase as a way out

### F12 · Deal card — share a win
**Does:**
- On a deal result, Share makes an image card showing:
  - the item
  - bought ₹200 → sold ₹1,800
  - the coin icon
  - the game's name
  - "Game money — no real money"
- Android's share sheet sends it wherever the player chooses.
- **This is the first feature to cut if v1 runs late.**

**Acceptance**
1. Given a deal result, when I tap Share, then Android's share chooser opens with the card.
2. Given I cancel the chooser, then nothing is sent.
3. Given the card, then it shows "Game money — no real money" and the coin icon. It carries no personal data, no IDs, no tracking link and no banknote image.

**When it fails:** if sharing fails, the game says "Couldn't share" and nothing else changes [A].

**Does not:** post anything by itself; reward sharing [A]; include a referral code.

### F13 · Voices, text and numbers
**Does:**
- Characters speak in the chosen voice — English, Tanglish or Hinglish — all in Latin script.
- Menus and Settings are plain English in every voice.
- Bazaar words (kanakku, godown) are the same in all voices [A — the Hinglish reader may ask for changes, Q2].
- Numbers use Indian grouping and lakh/crore short forms, and ₹ appears with a stylised coin icon.
- Text size is adjustable.

**Acceptance**
1. Given each voice, then every character line exists in it. A build fails if any voice has a missing line, a broken placeholder, an over-length line or a banned word (the validator).
2. Given cash of 125000, then it reads ₹1,25,000. Where space is short, 250000 reads ₹2.5L and 12000000 reads ₹1.2Cr.
3. Given the largest text size, then no line on the haggle screen is cut off [A].
4. Given the Hinglish voice, then a native Hindi speaker has read every line before the closed test. This is a release blocker (Q2).
5. Given any amount of money on screen, then no banknote image appears.

**When it fails:** a missing line falls back to English (F1).

**Does not:**
- use Tamil or Devanagari script in v1
- machine-translate
- take typed input
- use voice audio

### F14 · Sound and haptics
**Does:**
- Basic sound effects.
- A mute button from the very first screen: on the voice picker [A], on the stall, and in Settings.
- Vibration on/off [A].

**Acceptance**
1. Given the first screen, then a mute control is there.
2. Given I mute, then all sound stops at once and stays off after a relaunch.
3. Given vibration is off, then the phone never vibrates for the game.
4. Given every sound, then it is CC0 or made by the owner (jsfxr), and is listed in Credits wherever a licence asks.

**When it fails:** nothing to fail.

**Does not:** play music in v1 [A]; use casino-style sounds — coin showers, jackpot bells, slot reels.

### F15 · Saves, resume and the back button
**Does:**
- The game saves after every round and resumes exactly where the player left off — mid-haggle after a call, or after Android kills it.
- Two rotating backups are kept on the phone.
- Android's Auto Backup copies saves and settings, and nothing else, to the player's own Google Drive.
- A damaged save is recovered from a backup, and a full phone is reported.
- Back never quits by accident.

**Acceptance**
1. Given a haggle in progress, when I force-stop the game and reopen it, then the same customer, item, offers, mood and patience are on screen.
2. Given a deal, bid, payment, scrap, loan or upgrade was happening when the game died, when I reopen, then it is either done (and counted once) or not done — never both, never twice.
3. Given a damaged save, then the newest good backup loads and the game says "Couldn't read your save; restored your backup from <time>."
4. Given a full phone, when the game saves, then:
   - it says "Couldn't save — phone storage full"
   - the last good save stays intact
   - it tries again at the next save
5. Given a new phone on the same Google account with Android backup on, when I install the game, then my progress and settings come back.
6. Given Android 16 with gesture navigation, and again with 3-button navigation, when I press back, then:
   - it steps back one screen
   - at the stall, it asks before leaving the game
   - mid-haggle, it pauses — it never walks away from a customer or quits by accident
7. Given a save written by a newer version of the game, when an older version opens it, then it leaves the save untouched and says the game needs an update [A].

**When it fails:** the messages above. Never a silent, blank new game.

**Does not:**
- offer cloud save through Play Games in v1
- offer manual save slots — saving is automatic
- send saves anywhere

### F16 · Settings
**Does:** One screen, reachable from anywhere in the game. It holds:
- voice
- sound and mute
- vibration
- text size
- the privacy policy (opens in the browser)
- credits & licences
- the app version and save-format version
- Share my stats (F18)
- Report a problem (F19)
- Start over (F17)

**Acceptance**
1. Given any screen, then I can open Settings. Opening it mid-haggle or mid-auction pauses that haggle or auction.
2. Given I tap Privacy policy, then it opens in the phone's browser at its public URL.
3. Given Credits & licences, then it lists every font, sound, icon and library, with its licence and any notice that licence requires.
4. Given Settings, then the app version and the save-format version are shown.

**When it fails:** with no browser on the phone, the game shows the policy's address to type in elsewhere [A].

**Does not:** have accounts, sign-in, or anything to type.

### F17 · Start over
**Does:** Deletes everything the game keeps on this phone:
- the save
- both backups
- settings
- the age and consent records, once ads exist

The next launch is a first launch.

**Acceptance**
1. Given I tap Start over, then the game asks twice, and says it can't be undone [A — two steps].
2. Given I confirm, then the next launch shows the voice picker and nothing of the old game remains. Android's App info shows the game's data back at its fresh-install size.

**When it fails:** if the player reinstalls before Android's next backup runs, the old progress can come back from Google Drive. That timing is Android's schedule, not the game's.

**Does not:** reach the copy in the player's Google Drive directly; touch waitlist or support emails, which are deleted on request by email (H2-11).

### F18 · Share my stats
**Does:**
- The player's own numbers as plain text, sent only through Android's share sheet.
- The text holds [A — the list]:
  - first and last date played
  - days played
  - in-game days started and finished
  - haggles in total, and per day played
  - deals and the best deal
  - current tier
  - play time to reach the shop
  - app and save versions
- Testers use it to report. It's also how the success checks are measured (§12.2).

**Acceptance**
1. Given I tap Share my stats, then I see the full text first, and then Android's share chooser opens.
2. Given the text, then it holds only game numbers and versions — no device, account or advertising IDs, and no name.
3. Given I've just installed, then it shows zeros, not an error.

**When it fails:** the game says "Couldn't share" and nothing else changes.

**Does not:** send anything by itself; run in the background.

### F19 · Report a problem, Report this line, and the crash note
**Does:**
- **Report a problem** (Settings):
  1. Pick a reason.
  2. Read the whole message.
  3. The phone's email app opens, addressed to the game's email.
- The message carries the app, save and Android versions — never IDs — and the last crash note only if the player ticks it.
- **Report this line:** long-press any line to open a report with that line filled in.
- **Crash notes:** after a crash, the phone keeps the last three crash notes, with no IDs. They leave the phone only inside a report the player sends.
- Closed testers use this for feedback.

**Acceptance**
1. Given Report a problem, then only reasons that apply to this version are listed [A]:
   - v1: bug, lost progress, a line that offends
   - "a bad ad" appears once ads exist; "a purchase" once purchases exist
2. Given I pick a reason, then I see the whole message before my email app opens, and nothing is sent until I press send there.
3. Given the game crashed earlier, then I can tick "Include the crash note" and read it before sending.
4. Given any line of dialogue, when I long-press it, then Report this line opens with that line's text filled in.
5. Given no email app is set up, then the game shows the game's email address to write to [A].

**When it fails:** as in criterion 5.

**Does not:**
- send anything automatically
- include device, account or advertising IDs
- upload crash data — Android vitals in Play Console covers crashes from players who share diagnostics with Google

### F20 · Offline, small and light
**Does:**
- Every v1 feature works with no connection. v1 has no internet permission, so it can't send anything anywhere.
- It runs in portrait only, on Android 7.0 and later.
- It runs smoothly on 2–3 GB RAM phones.
- The download is about 50 MB or less.

**Acceptance**
1. Given airplane mode from install onward, then every v1 feature works exactly the same.
2. Given the release build in Play Console's App bundle explorer, then the only device permission listed is vibration. The only other entry allowed is the app's own `…DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION`, which AndroidX adds for its internal receivers and which grants no access to anything (TRD TD-24).
3. Given a 2–3 GB RAM phone, then the haggle, the auction and the reveal run without visible stutter [A — needs a test phone, Q13].
4. Given the Play listing, then the download size is about 50 MB or less.
5. Given I turn the phone sideways, then the game stays in portrait.
6. Given Android 7.0 (API 24) or later, then the game installs and plays.

**When it fails:** nothing to fail.

**Does not:** need the internet for anything in v1; ask for any runtime permission.

---

## 5. Not in v1

**Waits for G2 — Milestone 4: ads, after the public release in India**

| What | Why it waits | What brings it in |
|---|---|---|
| Rewarded ads, opt-in only:<br>• an extra 10-second peek<br>• reopening a haggle after a walk-away, once per customer<br><br>At most 12 a day. A reward only for a completed ad. The game saves before every ad | v1 must first prove that players come back. Ad money only follows retained players | G2 passed and the game is public in India |
| Ad compliance, all in the same release as the ads SDK:<br>• the age screen and ads consent<br>• Settings → Ads & privacy, and Settings → Your data<br>• Periyappa's tip for players under 18 or of unknown age<br>• privacy policy v2, the Tamil and Hindi notice pages, app-ads.txt<br>• the Data safety update | Needed only once there are ads | Milestone 4 |
| The ad offers screen, with a "no ad right now" state | Same | Milestone 4 |

**Waits for G3 — the depth, once free channels prove they bring players**

| What | Why it waits | What brings it in |
|---|---|---|
| Restoration on timers, at a fixed time and cost and never a chance of failure:<br>• dust and rust visibly peeling off<br>• the workshop<br>• instant finish by rewarded ad | v1 tests the core verb. Pawn Shop Master reached 10M installs without restoration | G3 passed |
| Assistants; offline income (capped); the "while you were away" Welcome back screen | Real-time play only matters once restoration exists | G3 |
| Notifications:<br>• scheduled on the phone<br>• only for something the player started<br>• at most one a day<br>• never about ads or purchases<br>• explained before Android's prompt, at the first restoration | Nothing in v1 runs while the game is closed. Retention mustn't depend on notifications, because many Indian phones' battery savers block them | Restoration |
| More cities — Chennai, Madurai, Mumbai, Delhi — each with its own items and customer types; the market map. Content grows to 150+ items and 15+ customer types | Content costs time. v1 tests whether the haggle holds first | G3 |
| Indian seasons on an in-game calendar (Diwali rush, Pongal clearance, wedding-season brass and silver, monsoon godown damage); the event card | Same | G3 |
| Collections with set bonuses; the Dealer's Guide; item detail; reputation detail; the Help page | Same | G3 |
| Upgrades: magnifier, more display slots, storage, workshop slots | They need the depth above | G3 |
| Rival bluffing; godown-clearance auctions; 2–4 rivals | v1's auction is lite | G3 |

**Purchases — wait for money, not a gate**

| What | Why it waits | What brings it in |
|---|---|---|
| Cosmetic purchases only:<br>• Dealer Pack ₹149 — a theme set and a stall nameboard style<br>• cosmetic themes ₹49<br>• restore purchases and "payment pending"<br>• the shop screen<br>• terms, refund policy and grievance page | The owner's rule: let the game make money first. A single sale brings GST, e-commerce seller duties, and the owner's address on Play | In order:<br>1. AdMob's first payout (about ₹9,293)<br>2. a CA's GST answer, paid from that payout<br>3. the terms, refund and grievance pages<br><br>Best case about August 2027 [A] |

**Later, with no gate set**

| What | Why it waits | What brings it in |
|---|---|---|
| Cloud save and achievements through Play Games | They would give us each player's Play Games ID | Redo the data table first (§8.5) |
| Google's in-app review prompt — at most once, after a big win | Not needed for the closed test | After the public release [A] |
| Voice lines (audio), more languages, regulars' story arcs | Cost and download size | The owner's call, after G3 |
| Music | v1 has sound effects only [A] | A CC0 track that fits the size budget |
| Native-script Tamil and Hindi menus | Needs an Indic text renderer; the voices are in Latin script | Q10 |
| Throw-ins ("add the small lamp and I'll take it") and bohni (the first sale of the day) | Not named in IDEA's v1 | Q8 |
| In-game seasons shown as Play "Events and offers" cards; Play Pass | Seasons must exist first; Google decides Play Pass | After seasons ship |
| Countries outside India | Each brings its own law — for EEA/UK, Google's consent form and an EU representative | Re-run RESEARCH L-13 first |
| iOS | Not planned | — |

**Dropped — decided; bringing one back means changing this file first**

| What | Why |
|---|---|
| Full-screen ads the player didn't ask for (interstitials) | The USP is false while they exist |
| A "Remove ads" purchase | There's nothing to remove |
| Content updates fetched from the web | A hijacked account could push any line to every player overnight. It would also send every player's IP address to the host, and the game would need the internet |
| The Dealer Pack's starter assistant | An assistant earns in-game cash, and nothing sold may convert to it |
| Ad mediation (other ad networks) | Each network is one more company receiving player data |
| Analytics, crash-reporting and attribution SDKs | Play Console, Share my stats, Android vitals and AdMob's own reports cover every gate |

**Known limits — decided; changing one means changing the engine**

| Limit | Why | What would change it |
|---|---|---|
| Screen readers (Android's TalkBack) can't read the game | The engine draws its own screens, and Android's accessibility services can't see inside them (TRD §11, decision 1). What the game offers instead: adjustable text size (F13), and mood shown as a face plus a word, never colour alone (F2) | Making screen-reader support a requirement, which means revisiting the engine (TRD TD-01) |

**Never, in any version**
- **A money game:**
  - no stakes, entry fees or prizes
  - no cash-out, and no trading items or cash for real money
  - no selling in-game cash, or anything that converts to it
  - no paid randomness — no lots or random items for real money
- **Online:** no accounts, sign-in, multiplayer, chat, guilds, leaderboards, trading between players, or server. Anything shared between players needs a server and a redesign first.
- **Ad-gated:**
  - no forced ads
  - no pay-or-watch-or-lose gates
  - no second (premium) currency
  - no energy limits
  - no chance-based repairs or appraisals
- **Pushy:**
  - no fake urgency or countdown offers
  - no guilt buttons
  - no streak penalties
  - no nagging — one review prompt at most, ever
  - no ads disguised as game content
  - no lot contents tuned to spending or to ads watched
- **A different kind of game:**
  - not an idle clicker — no tapping for money
  - not a story game — no cutscenes
  - no side businesses (hotels, fishing, factories)
  - no locked tutorials
  - no live events or limited-time offers
  - not a live service — content ships only in app updates
- **Real-world:** no real brands, shops, people, idols, coins or banknotes. Not a guide to real antiques or prices.
- **For children:** the game is declared 18+, and no ad ever reaches a player under 18.
- **Pay-to-win:** a purchase never sells what play earns in reasonable time.
- **Typing:** no names, chat or free text anywhere.
- **Running cost:** no server or paid service. The game costs ₹0 a month to run.

---

## 6. Screens

**v1: 9 screens plus overlays.** Every list screen has two empty states: one for never having had anything, and one for a filter that hides everything.

| # | Screen | Purpose | What it shows with no data |
|---|---|---|---|
| 1 | Voice picker | First launch: choose English, Tanglish or Hinglish, with a sample line in each; mute | It *is* the no-data screen |
| 2 | Stall (home) | Cash, reputation, day, display slots, next-goal bar, Today's special, mute; the route to everything | At first launch:<br>• the starting cash<br>• the goal bar at 20% — Periyappa's gift<br>• empty display slots marked "Empty"<br>• a walk-in seller already waiting |
| 3 | Haggle | Face, mood word, patience, item, slider, quick buttons, offers with reasons | Never opens without a customer and an item |
| 4 | Inspect | Clues, condition, whether the clues point to a fake | Never opens without an item. Clues not yet found read "Look closer" [A] |
| 5 | Auction | Peek → live bidding → result with every rival's estimate | Today's auction done: "Next auction: tomorrow morning". Too little cash: the peek plays and the bid button says "Not enough cash" |
| 6 | Lot reveal | The gunny bag emptied item by item; keep or scrap | A lot is never empty [A]. An all-junk lot offers "Scrap all for ₹X" |
| 7 | Godown | All items, filter and sort, "Found X of 40" | Never had items: "Your godown is empty. Buy at the morning auction or from walk-in sellers." Filtered to nothing: "Nothing matches this filter" and a Clear button |
| 8 | Day end (kanakku) | Best deal first, profit, reputation, today's deals, best deal ever, tomorrow's teaser, upgrade | No deals: "No deals today", then tomorrow's teaser. No best deal ever yet: "Your first deal will show here." |
| 9 | Settings | Voice, sound, vibration, text size, privacy policy, credits & licences, versions, Share my stats, Report a problem, Start over | Share my stats shows zeros before any play |

**v1 overlays and messages**

| Overlay | Purpose / text |
|---|---|
| Deal result | Profit or cost, the verdict line, reputation change, Share (never empty) |
| Tier-up | The stall → shop celebration, once |
| Low cash | Loan, scrap, or a cheap-seller day |
| Hints | One line, the first time each thing appears [A] |
| Report a problem | Reason, the whole message, the crash-note option (only when a note exists), then the email app |
| Credits & licences | Inside Settings |
| Save recovered | "Couldn't read your save; restored your backup from <time>." |
| Storage full | "Couldn't save — phone storage full." |
| Newer save | "This save is from a newer version — update the game." [A] |
| Leave the game? / Leave the auction? | Back-button confirmations |

Development builds also carry two dev screens (a text-and-content test and the haggle lab). Release builds don't.

**Screens that arrive later**

| Screen | Purpose | Arrives with |
|---|---|---|
| Workshop | Restoration slots, timers, fixed costs and value gained, instant finish by ad | Restoration (after G3) |
| Assistants | Hire and assign | After G3 |
| Welcome back | What finished while you were away | After G3 |
| Upgrades | Tier, display slots, storage, magnifier, workshop slots | After G3 |
| Market map | Cities, unlock rules, travel | After G3 |
| Event card | What the season changes, and for how long | After G3 |
| Collections · Dealer's Guide | Set progress and bonuses · items discovered, value ranges learned, clue tips | After G3 |
| Item detail · Reputation detail · Help | What you paid, estimate range, history · trust and what moves it · one page on mood, patience, reputation and auctions | After G3 |
| Ad offers | Extra peek, reopen a haggle (and instant finish once restoration exists); a "no ad right now" state | Milestone 4 |
| Age screen + ads consent | Year of birth, then (for 18+) the notice: *Allow & watch* / *Not now* | Milestone 4 |
| Ads & privacy · Your data | Withdraw consent · everything kept on this phone, with Share and Start over | Milestone 4 |
| Notification explainer | Shown before Android's one-shot permission prompt | Notifications |
| Shop | Dealer Pack, themes, restore, payment pending | Purchases |
| Rate prompt | Google's in-app review, after a big win, once ever | Later |

---

## 7. ⭐ The rules of the domain

This section holds the business logic that isn't obvious. Each rule says why when the why isn't plain.

### 7.1 Money
- **DR1 · One currency: cash.**
  - It is earned only by playing.
  - It is never sold, bought, converted, traded or cashed out.
  - There is no second currency.
  - *Why:* India's online-gaming law tests for exactly these things (L-1), and "no premium currency" is part of the USP.
- **DR2 · Cash never goes below ₹0.** You can't offer, bid or pay more than you hold [A]. *Why:* no debt spiral — DR4 handles being broke.
- **DR3 · Periyappa's loan:**
  - interest-free, with no deadline
  - offered only when the player is broke
  - repaid automatically from a share of each sale [A — the share is a tuning number]
  - one open loan at a time [A]
- **DR4 · There is always a way forward.** From any state:
  - scrap sells at once, at its scrap price
  - a walk-in seller the player can afford always comes
  - the loan is there when broke

  The simulator must show no dead end.
- **DR5 · Nothing depends on money spent or ads watched.** Not lots, not customers, not prices, not haggle outcomes — not in v1, and not after ads and purchases arrive.

### 7.2 Haggling — fair, but not solvable
- **DR6 · The walk-away price is fixed when the haggle starts.** It never moves in response to the player's offers. Mood and patience change how the customer responds, not where they'd walk away [A — if the haggle lab changes this, the verdict must say which price it reports]. *Why:* the verdict line has to be true, and "rigged!" is the genre's standing complaint.
- **DR7 · Hidden until the deal is done.**
  - During a haggle, the walk-away price is never shown, never hinted as a number, and never leaked by a button.
  - After a deal, the verdict shows it exactly.
  - After a walk-away it isn't shown; a "Lesson: …" line says why instead [A].
  - *Why:* showing it after a walk-away would make reopening a haggle (a later ad reward) a sure thing.
- **DR8 · No deal past the line.** A customer never agrees to a price past their walk-away price. If patience runs out while the player's offer already meets it, they accept rather than walk [A].
- **DR9 · Customers don't know your cost.** A walk-away price comes from the item, its condition and the customer's personality — never from what the player paid [A]. *Why:* Dealer's Life 2 players complain that buyers anchor on what they paid.
- **DR10 · Every round explains itself:** one line on what changed in the customer's mood, and why.
- **DR11 · No single habit wins.** Each personality punishes one habit (F3). The simulator's five bots must each lose to at least one personality. *Why:* if one opening offer and one step size beat everyone, haggling becomes a chore by day three.
- **DR12 · Patience counts rounds, not seconds.** No haggle has a real-time clock [A]. *Why:* calls and app switches are common on cheap phones, and resume must be exact.
- **DR13 · "Last price?" once per haggle.** The answer is a firm price the customer will accept at once. It isn't promised to be their walk-away price [A].
- **DR14 · One call-back.** After the player walks away, a customer calls back at most once. A customer who has left doesn't return that day [A]. Later, a rewarded ad can reopen one haggle per customer.

### 7.3 Auctions and lots
- **DR15 · A lot is fixed before the peek** and never changes after it — not with bids, and not with how well the player is doing [A].
- **DR16 · Rivals see what the player sees.**
  - A rival sees only the peek, and bids from its own estimate, budget, risk appetite and favourite categories.
  - A rival never sees the hidden contents or the player's cash [A].
  - A rival never bids past its budget, and never bids while the game is paused.
  - *Why:* "rivals never cheat".
- **DR17 · Bids are final.**
  - A bid can't be withdrawn.
  - A new bid must beat the current one.
  - When the countdown ends, the highest bid wins [A for the countdown].
- **DR18 · Leaving an auction asks first.** Leaving means you don't win, and nothing is paid [A].
- **DR19 · After every auction, every rival's estimate is shown** — whether you won or lost.

### 7.4 Items, fakes, reputation and content
- **DR20 · Fake or genuine is decided when an item is created** and never changes.
- **DR21 · Clues never lie.** Every fake has at least one clue that gives it away to a player who inspects [A]. What inspecting costs, and how sure a clue is, are open (Q4).
- **DR22 · Bad faith costs reputation:**
  - lowballing a collector on something they love
  - selling a known fake at a genuine price

  An unknowing sale is open (Q5).
- **DR23 · Reputation touches future customers only.** It never changes the haggle in progress. It can always be rebuilt, and at its lowest, customers still come.
- **DR24 · The content rules hold in every line and every item:**
  - no real brands, people, shops, films, coins, stamps, banknotes, idols or temple artefacts
  - no personality tied to a region, religion, caste or community
  - women as well as men

  The validator checks words; a human read checks the rest.

### 7.5 Days and time
- **DR25 · In-game time moves only when the player acts.** In v1, nothing happens while the game is closed — no timers, no decay, no penalties.
- **DR26 · Today's special follows the phone's calendar date.**
  - One per calendar day.
  - Missing a day costs nothing.
  - If the date goes back by 1–2 days, nothing new appears until it passes the last date seen.
  - A jump back of **more than 2 days** counts as a clock correction: the special resets to today. *Why:* a phone whose date was wrong by a year mustn't lose specials for a year. Gaming this only changes the cheater's own game (H5-1). *(Decided 2026-11-24.)*
- **DR27 · A teaser is a promise.** What tomorrow's teaser names happens the next in-game day [A].
- **DR28 · Returning is welcomed, never punished.** After a gap, one "Welcome back — here's a cheap starter lot" [A — the gap's length is a tuning number]. Never a guilt message.
- **DR29 · The day ends on a high.** The kanakku leads with the best deal and ends on tomorrow's teaser. A loss is a "Lesson: …", never the last word.

### 7.6 The maximums

| What | v1 | Later |
|---|---|---|
| Auctions per in-game day | 1 | More, with godown auctions |
| Rivals per auction | 2 | 2–4 |
| Peek | 10 seconds | +10 s with a rewarded ad, once per auction [A] |
| Customers per in-game day | 5–8 [A] | |
| Rounds per haggle | Until patience runs out; patience is set per personality [A] | |
| "Last price?" | Once per haggle [A] | |
| Call-backs after a walk-away | Once per haggle [A] | |
| Reopening a haggle | Never | Once per customer, by rewarded ad |
| Clues per item | 2–3 | |
| Items / customer types | 40 / 8 | 150+ / 15+ |
| Regulars | 2–3 | Story arcs later |
| Open loans | 1 [A] | |
| Today's special | 1 per calendar day | |
| Bid or offer | No more than your cash. A rival: no more than its budget | |
| Cash | Never below ₹0 [A] | |
| Rewarded ads | 0 | No more than 12 a day |
| Full-screen ads | 0 | 0, ever |
| Notifications | 0 | No more than 1 a day |
| Review prompt | 0 | 1, ever |
| "Minor" re-check | — | Once a year |
| Backups on the phone | 2 | |
| Crash notes kept | 3 | |
| Permissions | Vibration only | Ads add internet, network state and ad ID. Purchases add billing. Notifications add the notification permission |
| Time to the first offer | 30 seconds from first launch | |
| Download size | About 50 MB | |
| Auto Backup | Android's 25 MB limit | |
| One in-game day | About 3–5 minutes [A] | |
| Stall → shop | 30–45 minutes of play [A] | |

### 7.7 Conflicts — what wins

| When… | …this wins |
|---|---|
| A call or app switch mid-haggle | The customer waits; play resumes at the same round |
| An app switch mid-auction | The auction pauses; nobody bids while you're away |
| The game dies mid-action (deal, bid, payment, scrap, loan, upgrade) | The action happens exactly once, or not at all |
| Back pressed mid-haggle | Pause — never a walk-away |
| Back pressed at the stall | The game asks before closing |
| Back pressed during bidding | "Leave the auction?" Leaving means not winning, and nothing is paid [A] |
| Patience runs out, but the offer already meets the walk-away price | The customer accepts |
| Reputation changes mid-day | It applies from the next customer |
| A damaged save | The newest good backup loads, and the player is told |
| Storage is full | The last good save is kept, the player is told, and the game retries at the next save |
| A save from a newer version | Left untouched, with "update the game" [A] |
| The phone's date moves back 1–2 days | No new Today's special until the date passes the last one seen |
| The phone's date moves back more than 2 days | A clock correction: Today's special resets to today (DR26) |
| The voice changes mid-game | The next line is in the new voice; nothing else changes |
| Start over vs the Drive backup | The phone is wiped at once; Android replaces the Drive copy at its next backup |
| The game is restored on a new phone | Saves and settings return. Once ads exist, age and consent are asked again |
| Text size vs line length | Lines wrap; nothing is cut off [A] |
| *Later:* an ad fails or is closed early | No reward and nothing lost — the game saved before the ad |
| *Later:* consent is withdrawn mid-session | Ad requests stop at once |
| *Later:* a purchase is pending | "Payment pending"; nothing is granted until it's paid |
| *Later:* a purchase is refunded | The item is re-locked at the next launch |

### 7.8 Who can see what

| Who | Sees | Never sees |
|---|---|---|
| The player | Their whole game: mood, patience and the reason each round; the exact walk-away price after a deal; every rival's estimate after an auction | The walk-away price during a haggle or after a walk-away; rivals' estimates before the hammer; whether an item is fake, except through clues |
| Rivals (game characters) | The peek, and their own budget | The hidden part of a lot; the player's cash [A] |
| Customers (game characters) | The offers on the table; the player's reputation | What the player paid |
| Other people | Only what a player chooses to send them (a deal card, stats, a report) | Anything else — there is no multiplayer |
| The owner | Play Console totals (installs, ratings, reviews, Android vitals); the reports and stats players choose to send; waitlist emails | Any player's save, progress or Drive backup; anything about a player who sends nothing |
| Google | The Auto Backup copy in the player's own Drive, under the player's account; crash reports from players who share diagnostics. *Later:* what the ads SDK collects from consenting adults, and purchase orders | — |

### 7.9 What is irreversible

| What | Why it's final | The guard |
|---|---|---|
| A closed deal | Deals are final in a bazaar | The verdict teaches instead |
| A bid | Bids are final | — |
| Scrapping | The item is sold | One confirmation, listing the items and the total |
| A tier-up | There's no going down | A tap at day end |
| Taking the loan | It's repaid from sales | The amount and the repayment share are shown before accepting |
| A reputation change | Only fair deals raise it again | Always shown with its reason |
| Start over | Everything on the phone is deleted | Two confirmations |
| *Later:* a completed purchase | Refunds go through Google Play | — |
| The app ID `io.github.rameshkumark24.bazaarboss` | A Play app ID can never change | — |
| Every save format ever released | Old saves must load forever | A migration test for every save version |
| Play App Signing, once on | It can't be switched off | The upload key is backed up (CR11) |
| *Later:* the owner's address on Play once purchases ship | Once shown, it's public | Accepted (H5-12) |

### 7.10 What must never happen twice

| Thing | Rule |
|---|---|
| Applying a deal | Cash, stock, kanakku and stats change once per deal, even across a crash |
| Paying for a won lot | Once |
| Periyappa's starting gift | Once per game (until Start over) |
| Today's special | Once per calendar day |
| The tier-up celebration | Once per tier |
| The found counter | +1 per kind of item, once |
| "Last price?" | Once per haggle [A] |
| A call-back after a walk-away | Once per haggle [A] |
| The welcome-back starter lot | Once per return [A] |
| First-time hints | Once each [A] |
| The waitlist email | One email to the list — the launch, or the news that the game won't launch — then the list is deleted |
| *Later:* an ad reward | Once per completed ad |
| *Later:* reopening a haggle | Once per customer |
| *Later:* the consent question | Once per notice version (and again after Start over) |
| *Later:* the age screen | Once (and again after Start over); "minor" can be re-checked once a year |
| *Later:* a purchase grant, and its acknowledgement | Once each |
| *Later:* the review prompt | Once, ever |

### 7.11 When ads and purchases arrive — rules decided now
- **DR30 · Ads only for consenting adults.** The ads SDK starts only when all three are true:
  - the age screen said adult
  - consent was given
  - that consent matches the current notice version

  Otherwise it never starts. For players under 18, or who didn't answer, ad offers become one free "Periyappa's tip" a day, and nothing in the game is locked.
- **DR31 · Every ad is one the player taps.**
  - Rewarded ads only — no full-screen ads.
  - Skipping one costs nothing.
  - Never at launch, at a customer's arrival, at an auction's start, or on the kanakku.
- **DR32 · A reward only for a completed ad, and only once.** The game saves before every ad. A failed, closed or interrupted ad grants nothing and costs nothing.
- **DR33 · At most 12 rewarded ads a day.** The count survives clock changes and resets the next day.
- **DR34 · Withdrawing consent stops ad requests at once,** and the SDK doesn't start at the next launch. A new notice version asks again.
- **DR35 · Age and consent records never leave the phone** and are never backed up. A restored or new phone asks again.
- **DR36 · Purchases are cosmetic only.**
  - Granting any product never changes cash, stock, reputation or progress.
  - Nothing sold converts to in-game cash.
  - There is no paid randomness.
- **DR37 · Prices come from Google Play.** They're shown as Play gives them, never written into the game.
- **DR38 · Grant only what's paid.**
  - A pending payment (UPI, for example) shows "payment pending" and grants nothing until it's paid.
  - Every purchase is acknowledged within 3 days.
  - Purchases are restored at launch and from Settings.
  - Refunded items are re-locked at the next launch.
- **DR39 · Notifications:**
  - no more than one a day
  - only for something the player started
  - never about ads or purchases
  - scheduled on the phone, with no push service

---

## 8. Compliance requirements

These are product requirements. The legal reasoning and sources are in RESEARCH.md (the L- and X-numbers). This is not legal advice. Where a professional is needed, the item says when.

### 8.1 Always, in every version
- **CR1 · A social game, never a money game** (Online Gaming Act 2025):
  - no stakes, entry fees, prizes, cash-out, or trading for money
  - in-game cash never sold or converted
  - no paid randomness
  - never claim to be "approved", "registered" or "certified"

  *(L-1, X1, X2)*
- **CR2 · Nothing looks or sounds like gambling.**
  - No slots, roulette or casino visuals; the reveal is a gunny bag.
  - Banned words are blocked in every voice.
  - Play's content-rating questionnaire is answered honestly: bidding is not wagering.

  *(X5; RESEARCH §3 #7)*
- **CR3 · Audience 18+ on Play.**
  - Restrict Minor Access stays off.
  - Content stays teen-suitable.
  - The listing and the icon face adults — no toddler styling.

  *(L-3)*
- **CR4 · Content and IP.** The content rules in DR24. Every line gets a human read, and the Hinglish lines get a native read before the closed test. *(L-12)*
- **CR5 · No dark patterns** (India's Dark Patterns Guidelines 2023). None of the 13: false urgency, basket sneaking, confirm shaming, forced action, subscription trap, interface interference, bait and switch, drip pricing, disguised advertisement, nagging, trick question, SaaS billing, rogue malware. *(L-9)*
- **CR6 · Assets and licences.**
  - Assets are CC0, CC BY with credit, OFL, or made by the owner.
  - Music is CC0 only.
  - No GPL or AGPL code in the shipped game.
  - Credits & licences lists every notice the licences require.

  *(L-11)*
- **CR7 · The store listing:**
  - The title is 30 characters at most ("Bazaar Boss: Haggle Tycoon" is 26).
  - Keywords appear in natural sentences.
  - No "#1" or "best", and no emoji in the title.
  - Screenshots show only what the build has.
  - It promises "no forced ads", never "no ads".
  - It never says "earn", "win cash", "approved" or "registered".

  *(RESEARCH §3 #5; H4-17)*
- **CR8 · Honest marketing.**
  - Testers and friends never post reviews posing as ordinary players.
  - Rewards are never traded for ratings.
  - Paid influencer posts are labelled "advertisement", "sponsored", "collaboration" or "paid promotion".

  *(L-9)*
- **CR9 · Play's technical gates at every submission:**
  - the target API level Play requires (API 36 today)
  - compatibility with 16 KB memory pages
  - back working on Android 16 in both navigation modes

  *(RESEARCH §0 #8; R8, R17)*
- **CR10 · India only**, until RESEARCH L-13 is re-run for a new country.
- **CR11 · Accounts and keys.**
  - 2-step verification on the Google account (Play Console, AdMob, the form) and on GitHub.
  - Play App Signing on.
  - The upload key in a password manager, plus an offline copy.
  - Keystores never in git.

  *(H2-12)*
- **CR12 · Contact and requests.**
  - One contact email for the game — ideally a separate free Gmail — used on Play, on the waitlist page and in the policy.
  - Removals within 7 days.
  - A private requests log: date in, acknowledged, done. Entries are kept 12 months after closing.
  - DPDP replies within 90 days, from 13 May 2027.

  *(H2-10, H2-11, L-2)*

### 8.2 v1 — before the first tester
- **CR13 · v1 sends nothing.**
  - No internet permission.
  - The only device permission is vibration. AndroidX also adds an app-private permission for its own receivers; it grants no access to anything (TRD TD-24).
  - No SDK that sends data.
  - A build that asks for any permission off the allowlist must not ship.

  *(H1-4, H2-8, L-4)*
- **CR14 · Play's Data safety form says "No data collected or shared",** and the advertising-ID declaration says "No". *(L-4)*
- **CR15 · A privacy policy before the closed test.**
  - It lives at a public URL on GitHub Pages, linked from Settings and from Play Console.
  - It is written only from the data table (§8.5).
  - It says:
    - who the developer is, and the contact email
    - that the game collects no personal data, and has no ads, analytics or accounts
    - that saves stay on the phone, and Android may back them up to the player's own Google Drive, which we can't access
    - that shares go only where the player sends them
    - what happens to emails and messages we receive, and that they're kept for 12 months
    - that the game is not designed for children
    - its effective date, and how changes are announced

  *(L-14, H2-1)*
- **CR16 · Auto Backup carries saves and settings only,** within Android's 25 MB, and the policy mentions it. *(H1-10)*
- **CR17 · The closed test.**
  - At least 12 of the 14 testers stay opted in for 14 days in a row.
  - Feedback is logged by tester number, not name.
  - Testers are never pushed to review.

  *(RESEARCH §3 #1, H3)*
- **CR18 · Release blockers.** No release while any of these is true:
  - Hinglish lines haven't had a native read
  - the Data safety form doesn't match the build
  - a tester build contains a real ad

  *(H5)*

### 8.3 At Milestone 4 — ads, all in the same release as the ads SDK
- **CR19 · The age screen.**
  - A neutral year-of-birth screen: nothing pre-filled, and no hint of the age needed.
  - It appears at the first tap on an ad offer, never at launch.
  - It keeps only adult/minor and the date — never the year.
  - "Minor" can be re-checked once a year in Settings.

  *(L-2, L-3, H1-5)*
- **CR20 · Consent** (DPDP s.6):
  - For adults only: a plain notice saying what Google collects, and why.
  - Buttons *Allow & watch* / *Not now*; nothing pre-ticked.
  - The record (choice, date, notice version) stays on the phone and is never backed up.
  - Withdrawal is in Settings → Ads & privacy and is as easy as giving consent.

  *(L-2, H2-2)*
- **CR21 · Notice languages:** English in the app; Tamil and Hindi as web pages on GitHub Pages. *(L-2; accepted gap H5-8)*
- **CR22 · Rewarded ads only.** No interstitials, and placements only where DR31 allows. *(L-5)*
- **CR23 · AdMob controls.**
  - Set in the AdMob console as well as in code: gambling, real-money-game and loan ads blocked; maximum ad content rating "T".
  - No mediation.
  - Players' "bad ad" reports are checked in AdMob's ad review center within a week.

  *(L-6, H1-7)*
- **CR24 · Test ads in every build that isn't a release build.** The real ad unit IDs are never in git, and testers are told in writing never to tap real ads. *(L-6, H4-4)*
- **CR25 · Disclosures, in the same release:**
  - privacy policy v2: Google Mobile Ads data, Google as a recipient, the age screen, consent and withdrawal, DPDP rights with a 90-day reply, a contact person
  - the Data safety form declares the Google Mobile Ads row
  - the advertising-ID declaration becomes "Yes"
  - app-ads.txt on GitHub Pages, with the developer website named in the listing
  - the permission allowlist extended with internet, network state and ad ID

  *(L-4, L-14, RESEARCH §3 #3)*
- **CR26 · DPDP duties from 13/14 May 2027:**
  - a published contact person
  - access, correction and erasure rights
  - grievances answered within 90 days
  - the complaint route to the Data Protection Board

  **ADVICE (ADV-1):** a privacy lawyer checks the age screen, our role alongside Google and the notice wording — by 13 May 2027 if ads are live by then, otherwise before ads go live. *(L-2)*
- **CR27 · EEA/UK:** Google's consent form (UMP) only if the game is ever distributed there. *(L-13)*

### 8.4 At purchases
- **CR28 · Preconditions:**
  - AdMob's first payout
  - **ADVICE (ADV-2):** a CA's answer on GST
  - **ADVICE (ADV-3):** a lawyer confirms our e-commerce seller status and the grievance details
  - live terms, refund policy, grievance page and in-game Help entry

  Google may then show the owner's home address on Play (accepted, H5-12). *(L-8, L-9, L-10)*
- **CR29 · Google Play Billing only,** with no steering to other ways to pay. The Billing Library version Play requires (8 or later today). Enrolled in the 15% service-fee tier. *(L-7)*
- **CR30 · The billing rules in DR37 and DR38.** *(L-7)*
- **CR31 · Cosmetic only (DR36).** Play Console's product list must equal the cosmetic list. There is no paid randomness, so there are no odds to publish. *(L-1, L-7)*
- **CR32 · Grievances.** A grievance officer, with name, designation and contact details. Complaints acknowledged within 48 hours and resolved within a month. The refund policy is honoured. *(L-9)*
- **CR33 · The permission allowlist adds billing.** *(H2-8)*

### 8.5 THE DATA TABLE

This is the live copy. The copy in IDEA.md → Hardening H3 is frozen as of 2026-11-24.
- **The privacy policy and Play's Data safety form are written from this table and nothing else.**
- A new SDK, field or form changes this table first, with a dated line in the [change log](#change-log). The policy and the form follow in the same release.

**SDKs in the app**
- **v1:** libGDX and AndroidX, which send nothing.
- **Ads:** adds the Google Mobile Ads SDK.
- **Purchases:** adds the Google Play Billing Library.
- **Never:** no analytics, crash, attribution or mediation SDK.

| What | Why | Kept where | Who else receives it | How long | Allowed by | On Play's Data safety form |
|---|---|---|---|---|---|---|
| **Now — the waitlist and the owner's inbox** | | | | | | |
| Email address | Send one email: the launch, or the news that it won't launch | Google Forms, in the game's Google account | Google (runs Forms) | Deleted within 30 days of that email; removed on request within 7 days | Consent — submitted for that purpose | — (a web form, not the app) |
| Preferred voice (optional) | Choose which voice to polish first | Same | Google | Same | Consent | — |
| "I'm 18 or older" tick | Keep children's emails off the list | Same | Google | Same | Consent | — |
| Visitors' IP address and browser details | Serve the page and the form; security | GitHub's and Google's servers; we never see them | GitHub (hosts the page and logs IPs "for security purposes"); Google (Forms) | Their own retention | Their terms | — |
| Requests log: who asked (email), what, when, what was done | Keep the promises (7 days; 48 h / 1 month; DPDP 90 days) and show they were kept | A private Google Sheet | Google | 12 months after closing | Legal duty | — |
| **v1 — the game** | | | | | | |
| Game progress: cash, stock, reputation, day, stats | Play and resume | The phone, plus 2 local backups | Android Auto Backup copies it to the player's own Google Drive; we can't read it | Until Start over or uninstall | The game needs it | No — it stays on the device |
| Settings: voice, sound, vibration, text size | Same | Same | Same | Same | Same | No |
| Deal card image; Share my stats text | The player chooses to share | Leaves only through Android's share sheet | Whoever the player sends it to | Not ours | The player's own action | No — user-initiated |
| Report or support email: the sender's address, the message, and anything they chose to include (versions, crash note) | Reply and fix | The game's mailbox | The mail provider (Google, if Gmail) | 12 months after the last message | Sent voluntarily for that purpose (DPDP s.7(a)) | No — sent from the player's own email app |
| Closed testers' email addresses | Run the closed test | Play Console's tester list | Google | Removed after the production launch | They agreed to test | — |
| Crash and freeze reports | Fix crashes | Play Console (Android vitals) | Google, which collects them through Android from players who allow usage & diagnostics | Google's retention | Google's own collection | No [A — confirm against Play's Data safety guidance at submission, Q16] |
| **Ads (Milestone 4)** | | | | | | |
| Adult/minor and the date asked — never the birth year | Keep ads away from under-18s | The phone; never backed up | Nobody | Until Start over or uninstall | The game needs it to obey the law | No — on the device |
| Ad consent: choice, date, notice version | Ads only with consent; proof on the phone | The phone; never backed up | Nobody | Replaced at each change; until Start over or uninstall | Same | No — on the device |
| Rewarded ads watched today | The 12-a-day cap | The phone | Nobody | Reset daily | The game needs it | No |
| What the Google Mobile Ads SDK collects and shares automatically: IP address (approximate location); product interactions (launches, taps, video views); diagnostics (launch time, hang rate, energy use); advertising ID, app set ID and, where applicable, identifiers of accounts signed in on the device. Encrypted in transit | Pay for the game with ads players choose | Google's servers | **Google (AdMob)** — no mediation, no other network | Google's retention | **Consent, adults only** (CR19, CR20) | **Yes — collected and shared**, for advertising, analytics and fraud prevention, from Google's own mapping, in the same release as the SDK |
| **Purchases** | | | | | | |
| Order number, item, price, date | Deliver, restore and refund | Google Play (the owner sees orders in Play Console). The app keeps only which items are owned | Google Play | Google's records; owned items until uninstall | The contract — the player buys | Whatever Play's guidance requires at that release [Q18] |

**What the forms say, from this table**
- **Privacy policy v1** (before the closed test): the waitlist rows and the v1 rows, as listed in CR15.
- **Data safety, v1:** "No data collected or shared". The ad-ID declaration says "No".
- **At ads:** privacy policy v2 adds the ads rows. Data safety declares the Google Mobile Ads row, and the ad-ID declaration becomes "Yes" — in the same release as the SDK.

**Never collected** (cut in Hardening H3; adding any of these means redoing this table first):
- names, phone numbers or cities
- a Google sign-in on the waitlist form
- the birth year
- an analytics SDK or install IDs
- a crash-reporting SDK
- player or shop names, or any free text
- push-notification tokens
- remote content requests
- the Play Games player ID
- purchase tokens after use
- other ad networks
- testers' names in the feedback log
- location, contacts, files, camera or microphone

---

## 9. Vocabulary

Pick one word and never drift.
- Menu labels and game terms are exact, in every voice.
- Characters' dialogue may use natural bazaar wording (a Tanglish seller may say "duplicate" for a fake), but never a word from the Never lists below.

| Say | Meaning | Not |
|---|---|---|
| **Bazaar Boss: Haggle Tycoon** (store title, 26 characters); **Bazaar Boss** for short | The game | — |
| **haggle** | One negotiation with one customer over one item, from the first price to a deal or a walk-away | negotiation, bargain session, battle |
| **round** | One offer and the reply to it | turn, move |
| **offer** | A price the player names in a haggle | bid (bids are for auctions only) |
| **counter** | The customer's reply price | — |
| **deal** | A price both sides agreed; the haggle ends | win, trade |
| **Walk away** | Ending a haggle without a deal — the player's button, and what a customer does | quit, cancel, leave, fold |
| **walk-away price** | The hidden price a customer won't go past. A docs word; players meet it only in the verdict | reservation price (IDEA's old word), secret price, max/min price |
| **verdict** | The one line after a deal, on what was left on the table | score, grade, rating |
| **Lesson** | The one line after a walk-away or a loss | fail, game over |
| **Last price?** | The quick button asking for a firm price | final offer, best price |
| **mood** · **patience** | How the customer feels (a face plus a word) · how many more rounds they'll take | anger meter · timer, energy |
| **customer** | Anyone who comes to the stall to deal. Say **walk-in seller** when they're selling to you | NPC, client, visitor |
| **personality** | Tough bargainer · emotional seller · rushed buyer · collector · con artist | class |
| **customer type** | One of the 8 characters built on a personality | — |
| **regular** | One of the 2–3 customers who come back and remember you | recurring NPC |
| **rival** | A game dealer bidding against you at an auction | opponent, bot, enemy |
| **kabadi auction** · **auction** | The morning auction | sale, bidding war |
| **lot** | What an auction sells as one | mystery box, crate, chest, loot, pack, bundle |
| **peek** | The 10-second partial look at a lot | preview, scan, x-ray |
| **bid** | A price in an auction | bet, wager, stake |
| **reveal** | Emptying the gunny bag, item by item | unboxing, opening, spin, draw |
| **scrap** | Selling junk at once, at its fixed scrap price | trash, destroy, delete |
| **item** · **condition** · **rarity** | A thing you can own · its state · how rare it is | product, card, loot |
| **inspect** · **clue** | Look closer · what looking finds | scan, appraise |
| **fake** · **genuine** | — | duplicate, counterfeit, original |
| **godown** | Where your items are kept | inventory, storage, bag, warehouse |
| **display** | The stall's slots that customers buy from | shelf, showcase |
| **stall → shop → showroom → gallery** | The tiers (gallery = antique gallery) | level, rank, store |
| **tier** | One step on that ladder | level |
| **day** | One in-game market day. Say **calendar day** for the real date | round, level, session |
| **kanakku** | The day-end accounts screen | ledger, report, summary |
| **Today's special** | One special lot or visitor per calendar day | daily reward, daily bonus, login bonus |
| **Tomorrow: …** | The teaser line at day end | — |
| **next goal** | The bar on the stall | quest, mission, objective |
| **reputation** | The one trust meter | karma, rating, honour, stars |
| **cash** (₹ with the coin icon) | The one in-game currency | coins, gold, gems, points, bucks, money or rupees on their own |
| **Game money — no real money** | The exact line on the deal card | — |
| **Periyappa** | The uncle behind the starting gift, the loan and, later, the tip. Same name in every voice [A] | uncle, bank, lender |
| **loan** | Periyappa's interest-free help | credit, debt, EMI |
| **deal card** | The share image | brag card, receipt, certificate |
| **voice** | English / Tanglish / Hinglish | language (for this setting) |
| **Share my stats** · **Report a problem** · **Report this line** · **Start over** | Exact Settings labels | Feedback, Reset, Delete account, Contact us |
| **no forced ads** | The store promise | no ads, ad-free |
| *Later:* **Watch an ad** | On every button that plays an ad | free, bonus, gift, claim |
| *Later:* **Periyappa's tip** | The daily alternative to ads for players under 18 or of unknown age | — |
| *Later:* **Ads & privacy** · **Your data** | Settings pages | privacy center |
| *Later:* **Dealer Pack** · **theme** | Cosmetic purchases | starter pack, booster, premium, VIP |
| *Later:* **payment pending** | A purchase waiting on UPI or similar | processing |

**Never, anywhere — including characters' dialogue, in every voice.** The validator blocks these; its banned-word list carries the Tanglish and Hinglish equivalents too (e.g. satta, juaa, soodhattam).
- **Gambling:** bet, wager, stake, gamble, casino, jackpot, slot, spin, lucky draw, lottery, odds, loot box, mystery box.
- **Real names:** any real brand, shop, film, celebrity, real person or deity.

**Never in menus, buttons, offers, notices, the store listing or marketing.** Dialogue is excused here: a rushed buyer may say they're in a hurry.
- **Money games:** earn money, earn cash, win cash, cash out, withdraw, redeem, prize, payout, reward money, and "real money" anywhere except "Game money — no real money".
- **Claims:** approved, registered, certified, government-approved, licensed, official.
- **About ads:** "no ads" and "ad-free" — the promise is "no forced ads".
- **Pressure:** hurry, only today, last chance, limited time, don't miss out, streak, "you'll lose…".
- **Guilt:** any button in the style of "No thanks, I like losing money".

**Never in the store listing:**
- "#1", "best" or "top" as claims about the game ("Best deal ever" inside the game is fine).
- Emoji in the title.
- "For kids", "children" or "family game" — the audience is 18+.

---

## 10. Open questions

None of these is settled anywhere. Each carries a **recommendation** — my suggested answer, not a decision. A question stays open until the owner accepts or changes the recommendation. Then the answer moves into the right section, with a line in the change log.

**Product decisions still open**

| # | Question | Decide by | Recommended |
|---|---|---|---|
| Q1 | **The waitlist test's result** — go at ≥ 1,600 sign-ups, stop below 370, and between them build only to G1 (§12.1) | Day 7 of the test. It blocks the build | Publish the page this week. It needs only your Google Form link and the game's email — about 20 minutes, per site/README.md. Then post the clip. Start no build work until the day-7 count is in, and don't move the thresholds after seeing it |
| Q2 | **Who reads the Hinglish lines as a native speaker?** Also ask that reader whether "kanakku" should read "hisaab" in Hinglish, and whether "Periyappa" works there | Before the closed test. It's a release blocker | Ask a native Hindi speaker among your 14 testers to read the Hinglish column (about 2 hours).<br>• Keep "kanakku" as the screen's name in every voice, and let Hinglish dialogue say "hisaab".<br>• Keep "Periyappa": he's the Coimbatore uncle, and Coimbatore is the setting.<br>• If nobody has read it in time, open the closed test with English and Tanglish only and add Hinglish in an update. Never ship it unread |
| Q3 | **Closed-test reminders vs Check 1.** Play's production-access review favours an engaged closed test, and RESEARCH suggests a daily nudge. But Check 1 counts testers who play on day 7 "with nobody reminding them". Decide what reminders may say (for example, "stay opted in" only), or measure Check 1 only on testers who got none | Before the closed test | Send testers three messages only:<br>• day 0: welcome, and how to report<br>• day 8: "please send Share my stats"<br>• day 14: thanks<br><br>Never ask them to play. If a tester goes quiet, check only that they're still opted in. Check 1 then counts every tester honestly |
| Q4 | **What inspecting costs, and how sure a clue is.** If inspecting is free and a fake clue is certain, the con artist never wins and fakes stop mattering. If it costs a round of patience, or a clue only suggests, skipping inspection becomes a real temptation | M2 (items and inspection), in the haggle lab | Each Inspect tap reveals one clue.<br>• Mid-haggle, each tap costs the customer one point of patience. In your own godown it's free.<br>• Clues are certain: a "wrong" clue proves a fake.<br>• A fake has at least one wrong clue among its 2–3, but not always the first.<br><br>So full inspection is possible but costs patience, and the rushed seller and the con artist pull you opposite ways. Confirm it in the haggle lab |
| Q5 | **Selling a fake you didn't know was fake** — does reputation drop when the buyer finds out? | With Q4 | Yes, a small drop — a third of the penalty for a knowing sale — shown at day end as a Lesson ("A buyer found out the lamp was fake"). It keeps inspection worth doing after you buy, without treating an honest mistake like fraud |
| Q6 | ~~What the next-goal bar counts~~ | **Decided 2026-11-24** | Profit earned since the last tier; the upgrade costs no cash once the bar is full; Periyappa's gift is a 20% head start. Now in F7 |
| Q7 | **What a v1 player meets after the shop.** The showroom shows as the next goal. When that bar fills, can they buy it (which needs showroom content), or does it say "Coming in an update"? | M3, before the closed test | From the moment the shop opens, show the showroom as "Coming in an update" — never as a bar that fills to nothing. Make the next goal "Find all 40 items" (the found counter), which v1 can deliver. Don't build showroom content for v1 |
| Q8 | **Throw-ins and bohni in v1?** IDEA counts them among the Indian-bargaining differentiators, but its v1 list doesn't name them | M1, in the haggle lab | Bohni in v1: one engine rule (a seller's first sale of the day comes a little cheaper, "for luck") and a few lines. Throw-ins after G1, because they need two-item deals on the haggle screen |
| Q9 | **The economy numbers** are set in tuning against the 30–45-minute pacing target, using the simulator. None is decided yet, so don't treat a placeholder as a decision:<br>• starting cash<br>• item, scrap and tier prices<br>• patience per personality<br>• what counts as broke<br>• loan size and repayment share<br>• display slots and any godown limit<br>• the welcome-back gap | M1–M3 tuning | Keep the numbers out of this file. Tune them with the simulator in M1–M3 and record each in the TRD's tuning table. Fix two early, because they shape the code: a godown limit (start at 60 items) and the welcome-back gap (start at 3 calendar days) |
| Q10 | **Native-script Tamil and Hindi menus later** — still wanted, now that the voices are Tanglish and Hinglish? | Before any work on them | No — unless players ask for it in reviews after G3. Latin-script voices are the product, and native script needs a text renderer this engine doesn't have |

**Answers only the owner has**

| # | Question | Recommended |
|---|---|---|
| Q11 | Where are the idle engine and the Bus Mudhalali text code — and are they still wanted, given Latin-script voices? This is for the TRD; if the code doesn't arrive by M0, the save system and number formatting get written fresh (about 1–2 days) | Don't wait for it: write the save system and number formatting fresh in M0. If the code turns up, copy in only what's better, and never modify the originals. The Bus Mudhalali Indic text code isn't needed for Latin-script voices |
| Q12 | For how many weeks does ~5 hours a week hold? | Commit only to the weeks up to G1 (about 8), then decide the rest with G1's result |
| Q13 | Can the owner draw (for art direction)? And is there a 2–3 GB RAM phone to test on? F20 needs one; borrowing is fine | No drawing needed: the look is built from shapes in code (IDEA D2). Borrow a 2–3 GB RAM phone before M2, when the auction and the reveal first run |
| Q14 | Which platform is the owner's social account on, and roughly how many followers? This moves one row of the reach estimate only | Post the test clip wherever you watch short video most (Reels or Shorts) — reach there comes from the platform more than from followers. Share the follower count only if you want the reach estimate redone |
| Q15 | Before the first sale: the CA's GST answer (after AdMob's first payout, by the owner's rule), and whether the grievance page must publish a phone number (ADV-3) | Keep your rule: no CA until the first payout. Ask the phone-number question at that same visit (ADV-3). If a number is required, decide then between your own number and a separate prepaid SIM |

**Facts to confirm at a set moment** (checks, not decisions)

| # | Check | When | Recommended |
|---|---|---|---|
| Q16 | That Play's content-rating questionnaire treats bidding as not gambling, and how Data safety treats Android-vitals crash reports | The first Play submission | Do both at the first internal-testing upload, before the closed test. Read the questionnaire's gambling definitions before answering. Follow Google's Data safety guidance on vitals data, and record the answer in §8.5 |
| Q17 | Whether AdMob's console can block "Gambling & betting" and loan ads, and offers frequency capping on rewarded units; and that Play labels the listing "Contains ads" | Milestone 4 | Do it the day the AdMob account is created (Milestone 4, slice 1): set the blocks and the "T" rating, try frequency capping on the rewarded unit, and save screenshots to the release checklist |
| Q18 | What Play's Data safety requires for purchase order data | Purchases | Check Play's Data safety guidance for Play Billing in the purchases slice, and update §8.5 before the form |

---

## 11. Accepted risks

These were decided in IDEA.md → Hardening H5. They stay decisions: reopen one only by changing it here.

**For v1**

| ID | The risk we live with | Why — and what the safe option would cost |
|---|---|---|
| H5-1 | Cheating in your own game: edited saves, clock changes, modified APKs | There's no server of ours, and tampering only changes the cheater's own single-player game. A server to stop it would cost money every month and need accounts — the opposite of the product |
| H5-2 | Auto Backup copies saves to the player's Google Drive | It's Android's feature, under the player's Google account, and we can't reach the copy. Turning it off would lose progress on a new phone — a top complaint in this genre |
| H5-3 | Fewer crash reports, with no crash SDK | Android vitals covers players who share diagnostics, and the crash note covers players who report. A crash SDK would put data collection on the Play form |
| H5-4 | Google holds the waitlist (Forms) and, with Gmail, the support inbox | There's no free alternative without our own server. The page says so |
| H5-5 | One Play account and one upload key are the only way to ship updates | 2-step verification and key backups reduce the risk, and Play App Signing lets Google reset a lost upload key. A second store (Indus Appstore) waits until there's something to protect |

**When ads and purchases arrive**

| ID | The risk we live with | Why — and what the safe option would cost |
|---|---|---|
| H5-6 | A minor lies at the age screen and gets ads | Real age checks need ID documents: they cost money and collect far more sensitive data. Restrict Minor Access would block every account Google thinks is a teen, and reach is what the business case hangs on. A neutral screen is the standard; ADV-1 confirms it's enough by May 2027 |
| H5-7 | Consent proof lives only on the player's phone | Sending consent records to a server needs a server and an install ID — more data about every player, not less. We can show the design, the code and every dated notice version. ADV-1 checks |
| H5-8 | The ads notice exists only in English, Tamil and Hindi | DPDP lets a player ask for a notice in any of the 22 scheduled languages, and translating all 22 costs money we don't have. ADV-1 checks |
| H5-9 | A gambling, loan or real-money-game ad slips past AdMob's categories | Blocking is Google's job; we add player reports and a weekly review. The only sure fix is no ads, which ends the money |
| H5-10 | Invalid traffic (bots, modified apps) gets the AdMob account suspended | The daily cap, rewards only for completed ads, and test ads for testers lower the risk; Google's filters do the rest |
| H5-11 | Cracked apps unlock cosmetics without paying | Checking purchases on a server means our own server, 2–3 build slices and a Google service-account key to guard. That would protect only buyers who'd crack the game rather than pay — a slice of a likely ~₹300 a month |
| H5-12 | The owner's home address on the Play listing, once purchases ship | The owner's decision, 2026-11-24. A business address costs money |
| H5-13 | Purchases may wait a long time | The owner's rule: no CA until ads pay out (about ₹9,293), and in the likely case that takes years. If the June 2027 checkpoint passes but AdMob hasn't paid by July, paying a CA out of pocket is the only way purchases make November (§12.4) |

**Not accepted — these block a release instead:** see CR18.

---

## 12. Success

### 12.1 Before anything is built: the waitlist test

**Status (2026-11-24): not run yet.** Record the sign-up count and the date here when it's done:
- **Day 7 count:** count the Google Form's responses on day 7.
- **Each post's views:** note them from each platform's own numbers.

The Business verdict, word for word:

> **TEST FIRST — rests on ASSUMED numbers (reach, share who buy, ad engagement).** ₹10,000 a month in November 2027 needs ~10,350 installs a month from free channels — RARE, against a likely 402 — so run the one-week waitlist test (§7) before the build. Go at ≥ 1,600 sign-ups; stop below 370.

> - **Between 370 and 1,599:** build only to G1 (~5 weeks), then re-run this verdict with real tester retention.

**How to read the quotes:**
- "§7" means the Business verdict's own §7, in IDEA.md.
- "this verdict" means IDEA.md → Business verdict.
- "IDEA §7" (in §12.4) is where the gates in §12.3 come from.

### 12.2 v1 worked if any two of these hold, a month after v1 reaches real players

| Check | Passes when | How it's measured |
|---|---|---|
| **1. They come back without being pulled** | ≥ 7 of the 14 testers are still playing on day 7, with nobody reminding them (v1 has no notifications) | Each tester sends Share my stats on day 8. A last-played date at least 6 days after the first-played date counts as still playing. What counts as "reminding": Q3 |
| **2. Haggling is the reason** | Testers average ≥ 20 haggles per day played, and most can name a specific customer or deal unprompted | Share my stats: haggles ÷ days played, averaged across testers. A day-7 check-in asks one open question — "What do you remember from the game?" — logged by tester number. Count the testers who name a customer or a deal |
| **3. The pacing promise holds** | The median time to the shop tier is 30–45 minutes, within ±25% of the simulator's prediction | Share my stats: play time to the shop, for the testers who reached it. Compare the median with the simulator's prediction, which is written down before the test starts |

### 12.3 The gates

| Gate | When | Go if | Otherwise | Measured by |
|---|---|---|---|---|
| **G1** | After M1 — the haggle engine and the haggle lab | ≥ 3 of 5 people keep playing the haggle screen past 10 minutes unprompted, or ask to keep playing | Rework the haggle (at most 2 rounds), then stop | The owner watches 5 people play, with a stopwatch, and notes the minutes played and whether each asked to continue |
| **G2** | The end of the 14-day closed test | Checks 1 and 2 | Fix the top reason testers stopped, retest once, or stop | Share my stats, the day-7 check-in and the feedback log |
| **G3** | 30 days after the public release | ≥ 1,000 installs from free channels, and D7 retention ≥ 10% | Rethink acquisition or the hook before building more content | Play Console: installs in the first 30 days (there are no paid campaigns, so every install comes from a free channel), and its day-7 retention |

### 12.4 The stop rule — word for word from the Business verdict

> **If the test says go — DO IT, with this stop rule:**
> - **G1 ≈ 5 Jan 2027, G2 ≈ 15 Mar 2027, G3 ≈ 21 Apr 2027**, as set in IDEA §7.
>   - G3 is ≥ 1,000 installs in the first 30 days and D7 ≥ 10%.
> - **Money: ≥ 4,200 installs in June 2027** — on a straight line to 10,350 a month in November (10,350 × 3.3 ÷ 8.1) DERIVED.
>   - **Below that, stop building content** and leave the game up as it is.
>   - **Then check AdMob's balance.** If installs pass but the balance won't reach ₹9,293 by the end of July, your "money first" rule holds purchases past November. That's the moment to decide whether to pay a CA from your pocket — the only way purchases still make the bar.

How each part is measured:
- **June installs:** Play Console's install count for June 2027.
- **AdMob's balance:** read from AdMob's payments page at the end of July 2027.

---

## Change log

| Date | Change |
|---|---|
| 2026-11-24 | Written from IDEA.md (including the USP changes, the Business verdict and the Hardening decisions), RESEARCH.md and COMPETITORS.md. This file becomes the source of truth, and the live data table moves here (§8.5). New in writing it:<br>• §7 writes down rules that IDEA implied but never stated; the ones that are real design choices are marked [A]<br>• Share my stats' contents, defined so the success checks can be measured (F18)<br>• open questions Q3–Q9, found while writing the rules |
| 2026-11-24 | Every open question in §10 now carries a recommendation. All of them stay open until the owner decides |
| 2026-11-24 | Applied the TRD's findings, as recommended:<br>• DR26, F6 and §7.7: a jump back of more than 2 days resets Today's special (a clock correction)<br>• §5: a Known limits entry — screen readers can't read the game<br>• Q6 decided: the goal bar counts profit earned since the last tier, and the upgrade costs no cash (F7, §6) |
| 2026-11-24 | Precision fix from the first build: F20 AC2 and CR13 name the app-private permission AndroidX adds (TRD TD-24). No product change |
