# Research — risks, legal and compliance, store rejection

> **Status:** 2026-11-23. Reads [IDEA.md](IDEA.md), which is the product. Competitors are the next step and are left out here.
>
> **Not legal advice.** Neither of us is a lawyer.
> - **ADVICE** marks an item that needs a professional, with *when* it is needed. Advice that can wait is not a launch cost.
> - **STANDARD** marks normal practice you can do yourself.
>
> **Source tiers:**
> - **T1** — the rule itself: law text, a regulator's own publication, the store's live policy page, the provider's official docs
> - **T2** — a reputable summary: a law firm, a vendor's own blog or press release, major news
> - **T3** — what people report
> - **T4** — my memory, not checked
>
> A finding that changes the design but rests only on T3/T4 is marked **UNVERIFIED**, with what would confirm it. `[S#]` IDs resolve in §4. Every link was checked on 2026-11-23.
>
> **Class** (what the business step acts on):
> - **STOPS IT** — no legal or practical way through
> - **COSTS** — there's a known way through; it says what it takes
> - **ROUTINE** — every app like this lives with it

**Facts I don't have — what I assumed.** A different answer changes the findings that cite it.
- **F1** Play account is personal and was created after 13 Nov 2023, so the 12-tester / 14-day rule applies. [assumption]
- **F2** The seller is an individual resident in India (Tamil Nadu): no company and no GST registration yet. [assumption]
- **F3** Distribution is India only at first. [IDEA §9]
- **F4** v1 has no ads, purchases, analytics, accounts or user content. [IDEA §8]
- **F5** Ads arrive after the closed test passes (G2); purchases come later still. [IDEA §8]
- **F6** Today is 23 Nov 2026. India's DPDP data-protection duties start on **13/14 May 2027** — about 6 months away.

---

## 0. What this research forces us to change

Each change links to the finding behind it. Where a change touches the product, it has been folded into IDEA.md (see the list at the end of this section).

1. **Target audience "18 and over", not 13+** (L-3). Indian law counts everyone under 18 as a child [S1]. Play tells developers to treat local-law minors as children [S14]. Declaring 13+ would therefore pull in Families policy duties [S15], and later DPDP parental-consent duties. Game content can stay teen-suitable.
2. **No ads for under-18s; consent before ads for adults** (L-2, L-4).
   - A neutral age screen comes before the ads SDK is ever started.
   - Under 18, or age unknown → the ads SDK is never initialised, and the game is fully playable without it.
   - 18+ → a plain consent notice naming Google as recipient, the first time the player taps "watch an ad". Record it on the phone; let them withdraw it in Settings.
   - This removes India's ban on targeted ads to children [S1 s.9(3)] from play entirely.
3. **Rewarded ads first; interstitials only for adults who consented, and only after the day-end kanakku screen** (L-5). A rewarded ad starts with the player's own tap, so consent fits naturally at that tap. Interstitials don't, and Google only allows them at natural breaks [S20].
4. **Ads-only before purchases** (L-8, L-9, L-10). A single in-app purchase:
   - publishes your full address on the Play listing [S24]
   - makes you responsible for GST [S35]
   - brings Indian e-commerce seller duties: a named grievance officer with phone numbers, 48-hour acknowledgement, 1-month resolution [S11]
   
   So purchases wait until a CA has confirmed your GST position and you've decided about the address.
5. **AdMob setup rules** (L-6):
   - Every tester build uses test ads.
   - Block gambling and real-money-game ad categories.
   - Set a maximum ad content rating.
   - Host app-ads.txt on your GitHub Pages site.
6. **A privacy policy before the first tester** (L-14), even though v1 collects nothing [S16].
7. **Store listing hygiene** (§3):
   - no gambling words, and no "approved/registered game" claims [S8]
   - title ≤ 30 characters (ours is 26)
   - keywords used naturally, not stuffed into the title [S23]
   - screenshots show only what v1 has
8. **Technical musts:**
   - libGDX 1.14.x (16 KB-aligned natives, which I checked in the binaries) [S27]
   - target API 36 [S26]
   - predictive back enabled through libGDX's manifest flag; otherwise the back button just exits the game on Android 16 [S28, S29]
   - Billing Library ≥ 8 when purchases arrive [S31]
   - Auto Backup within 25 MB, mentioned in the privacy policy [S30]
9. **Bake fonts at build time** (L-11). No FreeType library at runtime means one less licence credit and a smaller download.
10. **Closed-test discipline** (§3 #1):
    - Keep ≥ 12 of your 14 testers opted in for 14 days in a row.
    - Log their feedback; Play asks you to summarise it.
    - Testers must never click real ads, and must not be pushed to leave reviews.

**Checked, and changes nothing:**
- India's online gaming law — we are a social game, and registration isn't required unless notified.
- User-generated content duties — none; we have no user content.
- Subscriptions and auto-renew — none planned.
- iOS / App Tracking Transparency — no iOS build.
- Apple's account-deletion rule — no accounts.
- GDPR / CCPA — not offered in the EU or US.
- Real coins, banknotes and idols — already banned in IDEA.

**Folded into IDEA.md on 2026-11-23:**
- target audience 18+ (§6, §7)
- the age screen and consent rules for ads (§2 LATER, §4 screen 26, §8)
- ads-only before purchases (§2 LATER)
- font baking (§6)

---

## 1. Risk register

**Scoring:** Likelihood (L) and Damage (D) are each 1–5; Score = L × D, ranked highest first.

**Type:**
- **TECH** — hard to build correctly
- **DEP** — someone else's platform can change underneath us
- **OPS** — what breaks once it works
- **ADOPT** — why people won't stay
- **LAW** — a legal or policy exposure

"Design?" means the risk changes what we build.

| # | Risk | Type | L | D | Score | Design? | Class | What it takes / our response | Source |
|---|---|---|---|---|---|---|---|---|---|
| R1 | **Nobody finds it.** No marketing money, and Play search for "auction game" / "pawn shop" is crowded | ADOPT | 5 | 5 | 25 | Yes — shareable deal card, Tanglish/Hinglish keywords, WhatsApp-friendly moments | COSTS — your time on free channels (Shorts/Reels, WhatsApp groups, Reddit); planned in the business step | T4 **UNVERIFIED** → G3 install count confirms |
| R2 | **DPDP ads duties from May 2027:** consent for adults; parental consent and no targeted ads for under-18s | LAW | 5 | 4 | 20 | Yes — age screen, ads off for minors, just-in-time consent, records, withdrawal | COSTS — ~2–4 days of build plus one legal check (ADV-1) | [S1] [S2] [S3] T1/T2 |
| R3 | **Ad money per player in India is small**, so the money goal is missed | ADOPT | 4 | 4 | 16 | Yes — rewarded-first, retention before monetising, gates G2/G3 | COSTS — needs scale; India rewarded eCPM ≈ $0.70–4 vs $10–25 in the US | [S48] T3 **UNVERIFIED** → your own AdMob reports after launch |
| R4 | **Players leave in the first session** | ADOPT | 4 | 4 | 16 | Yes — first haggle < 30 s, no permissions, no sign-in, voice picker in one tap | COSTS — tutorial iteration | T4 **UNVERIFIED** → closed-test stats |
| R5 | **Economy pacing is off** (shop tier too fast or slow; dead ends; a dominant strategy) | TECH | 4 | 4 | 16 | Yes — headless simulator with bot habits; low-cash floor | COSTS — simulator build plus tuning | T4 **UNVERIFIED** → simulator vs testers (success #3) |
| R6 | **Haggling isn't fun, or gets "solved"** — the core bet | ADOPT | 3 | 5 | 15 | Yes — each personality punishes a habit; G1 playtest | COSTS — up to 2 rework rounds, then stop | T4 **UNVERIFIED** → G1 confirms |
| R7 | **Saved progress lost or corrupted** (process death, full storage, reinstall) | TECH | 3 | 5 | 15 | Yes — atomic writes, 2 backups, Auto Backup ≤ 25 MB | COSTS — ~1–2 days | [S30] T1 |
| R8 | **Back button exits the game on Android 16** (API 36 stops sending KEYCODE_BACK) | TECH | 5 | 3 | 15 | Yes — `enableOnBackInvokedCallback="true"` + libGDX 1.14.x support; test with gesture *and* 3-button navigation | ROUTINE | [S29] [S28] T1 |
| R9 | **Your privacy is exposed if you sell anything** — full address on Play [S24]; grievance officer phone numbers [S11] | OPS | 5 | 3 | 15 | Yes — ads-only first | COSTS — a business address or second number if purchases ship (paid only after revenue) | [S24] [S11] T1 |
| R10 | **Production access refused** after the closed test (low engagement, testers drop out, weak answers) | DEP | 3 | 4 | 12 | Yes — tester plan: daily nudge, feedback log, stats share | ROUTINE | [S25] T1, [S50] T3 |
| R11 | **AdMob account suspended** — testers or you clicking live ads (typically 30 days; repeat → disabled) | DEP | 3 | 4 | 12 | Yes — test ads / test devices for everyone, always | ROUTINE | [S36] [S53] [S54] T1/T2 |
| R12 | **Content runs out** — 40 items start repeating by day 3 | ADOPT | 4 | 3 | 12 | Yes — variants per item (condition, maker's mark, era, colour); personality × mood | COSTS — content design time | T4 **UNVERIFIED** → testers' "where I stopped" |
| R13 | **Nothing pulls players back in v1** (no notifications) | ADOPT | 4 | 3 | 12 | Yes — retention hooks (IDEA §3.2) | COSTS — included in M3-lite | T4 **UNVERIFIED** → day-7 numbers |
| R14 | **Shape-built art looks cheap**, so store screenshots don't convert | TECH | 3 | 4 | 12 | Yes — art-direction pass; screenshot A/B with testers | COSTS — design time | T4 **UNVERIFIED** → tester reactions, Play listing experiments |
| R15 | **Ad market moves** (eCPM drops, policy shifts) | DEP | 4 | 3 | 12 | No | ROUTINE | [S48] T3 |
| R16 | **Target audience mis-declared** (13+ = children in India) → Families duties or rejection | LAW | 4 | 3 | 12 | Yes — declare 18+ | ROUTINE once decided | [S14] [S15] [S1] T1 |
| R17 | **Play's yearly technical gates** (target API, 16 KB pages, Billing Library versions) | DEP | 5 | 2 | 10 | No | ROUTINE — a yearly update | [S26] [S27] [S31] T1 |
| R18 | **Platform policy changes** (ads, Families, target audience) | DEP | 3 | 3 | 9 | No | ROUTINE | [S20] [S15] T1 |
| R19 | **Tax admin once money arrives** (GST on sales and on Google's fee; income tax) | OPS | 4 | 2 | 8 | No (process) | COSTS — a CA, paid from revenue (ADV-2) | [S35] T1 |
| R20 | **Looks like gambling** to parents, press or reviewers | ADOPT | 2 | 4 | 8 | Yes — gunny-bag reveal, never slots; word ban; skill-anchored lots | ROUTINE | [S21] [S6] T1 |
| R21 | **Cultural or religious offence** (stereotypes, deities) | OPS | 2 | 4 | 8 | Yes — content rules plus human read-through | ROUTINE | [S40] T1 |
| R22 | **Romanised spelling drifts** ("sollunga"/"solunga") | TECH | 4 | 2 | 8 | Yes — style guide plus validator | ROUTINE | T4 |
| R23 | **Indian e-commerce seller duties** once purchases ship | LAW | 4 | 2 | 8 | Yes — grievance page and in-app Help entry | COSTS — ADV-3 | [S11] T1 |
| R24 | **Slow on a 2–3 GB phone** | TECH | 2 | 4 | 8 | No | ROUTINE — libGDX is light; test on a real cheap phone | T4 |
| R25 | **Tanglish/Hinglish limits reach** (other states, script-only readers) | ADOPT | 3 | 2 | 6 | No — English voice exists | ROUTINE | T4 |
| R26 | **libGDX is volunteer-run** and slow on Android changes | DEP | 2 | 3 | 6 | No | ROUTINE — 1.14.2 already meets 16 KB and predictive back | [S27] [S28] T1 |
| R27 | **Play fee structure changes** (new tiers already live in US/EEA/UK/AU/JP) | DEP | 3 | 2 | 6 | No | ROUTINE | [S33] T1 |
| R28 | **Support load** ("lost my progress", "rigged!", purchases) | OPS | 3 | 2 | 6 | Small — Help page, rival-estimate reveal, auto-restore | ROUTINE | T4 |
| R29 | **IP complaint or name clash** | OPS | 2 | 3 | 6 | No | ROUTINE — free trademark search (ADV-5 only if a clash) | [S41] T1 |
| R30 | **Play account terminated** — the only channel | DEP | 1 | 5 | 5 | No | COSTS — follow policy; Indus Appstore as a backup channel (no commission on in-app payments) | [S49] T2 |
| R31 | **Gaming regulator reclassifies or requires registration** | LAW | 1 | 5 | 5 | No — design already avoids every money-game factor | COSTS — ADV-4 only if notified | [S7] [S8] T1/T2 |
| R32 | **Clock changed to skip timers** | TECH | 5 | 1 | 5 | No | ROUTINE | T4 |
| R33 | **Pirated or modded APKs** | OPS | 4 | 1 | 4 | No | ROUTINE — no server to protect | T4 |
| R34 | **Idle engine / Bus Mudhalali code never arrives** | TECH | 4 | 1 | 4 | No | ROUTINE — write save + number format fresh (~1–2 days) | checked 2026-11-22 |
| R35 | **GitHub Pages down or changes terms** (privacy policy URL breaks) | DEP | 1 | 3 | 3 | No | ROUTINE | T4 |
| R36 | **Cost at scale** | OPS | 1 | 1 | 1 | No | ROUTINE — offline, no server: zero marginal cost, a real strength | — |
| — | **Moderation / abuse** | OPS | — | — | — | — | Not applicable — no user content, chat or multiplayer | IDEA §7 |

### STOPS IT — doors that are closed (no legal or practical way through)

| # | What can't be done | Why | Source |
|---|---|---|---|
| X1 | Cash prizes, paid entry, paid tournaments, or any stake with a chance of monetary or other enrichment | It becomes an online money game, which is banned. Skill doesn't help: the ban covers chance, skill or both | [S6] T1, [S9] T2 |
| X2 | Letting players sell or trade items or in-game cash for real money, or cash anything out | "Monetisation of in-game assets outside the game" is one of the regulator's money-game tests | [S7] T1, [S8] T2 |
| X3 | Targeted advertising, tracking or behavioural monitoring aimed at under-18s in India (from May 2027) | An absolute bar unless the Government exempts a class; games aren't exempted | [S1] s.9(3)–(4) T1, [S2] T1 |
| X4 | Showing or promoting ads for real-money games inside our game | Advertising money games, and aiding or facilitating it, is an offence | [S6] T1, [S9] T2 |
| X5 | Simulated casino content (slots, roulette, social casino) | Play bans simulated gambling content | [S21] T1 |

*Closed by your own rule, though the law and Play allow it with conditions:* selling mystery lots or random items for money. Play would require published odds [S22], and it breaks your no-paid-randomness rule. It stays out.

---

## 2. Legal and compliance picture

India first (F3). Other places only matter if distribution widens (L-13). Each finding gives: **what** it is · **why it applies to this app** · **what it forces** · source and tier · ADVICE or STANDARD.

### L-1 · India's online gaming law — we are a social game, and must stay one
- **What:**
  - The Promotion and Regulation of Online Gaming Act, 2025 bans "online money games" — games played by paying fees, depositing money or other stakes expecting to win monetary or other enrichment, whether skill, chance or both — and bans advertising or facilitating them. Offering one carries up to 3 years and ₹1 crore; repeat offences up to 5 years and ₹2 crore. [S6] T1, [S9] T2
  - An "online social game" involves no stakes and no expectation of monetary winnings; one-time or subscription fees are allowed if they aren't a stake. [S10] T2
  - The Rules 2026 (notified 22 Apr 2026, in force 1 May 2026) register social games **only if the Government notifies a category**; e-sports always register. [S7] T1, [S8] [S51] T2
  - A game is classified by entry fees or deposits, bets or wagers, expectation of monetary or other rewards, revenue model, and how far in-game assets can be monetised outside the game. [S7] T1, [S8] T2
  - Providers **must not present a game as approved or registered** unless it is; must keep a grievance mechanism; and social-game providers must retain traffic data "as may be directed". Safety features (age gating, time limits) are required in detail only on applications, not for every game. [S8] T2
- **Applies because:** we have in-game cash, mystery lots, ads and later purchases, all used by people in India.
- **Forces:**
  - Keep every money-game factor at zero: no entry fees, no wagers, no cash-out, no player trading, and never sell in-game cash or random items (X1, X2).
  - Never write "Government approved", "registered" or "certified" anywhere.
  - Block real-money-game ads (L-6).
  - Keep one grievance inbox.
  - Fully offline v1 may not even be an "online game", since the definition needs operation through internet or electronic communication [S9] T2. That changes nothing.
- **ADVICE only if:** the Government notifies social-game categories for registration, a notice arrives from the Online Gaming Authority, or before selling anything that changes progress (e.g. the Dealer Pack assistant). Otherwise **STANDARD**.

### L-2 · Personal data — what the app touches, and what the DPDP law triggers
- **What the law says:**
  - The DPDP Act 2023 covers digital personal data processed in India. A child is anyone **under 18** (s.2(f)).
  - Processing needs consent (s.6) or a listed "legitimate use" (s.7). Advertising is not a listed use.
  - Consent must be "free, specific, informed, unconditional and unambiguous with a clear affirmative action" (s.6(1)), and withdrawal must be as easy as giving it (s.6(4)).
  - The data fiduciary — the business deciding why and how data is processed — must **prove** notice and consent were given (s.6(10)).
  - The notice must be available in English or any Eighth-Schedule language at the user's option (s.5(3)).
  - Fiduciaries must publish a contact person (s.8(9)) and run a grievance mechanism (s.8(10), s.13); the Rules require responses **within 90 days**.
  - Children: verifiable parental consent before processing **any** personal data (s.9(1)); no tracking, behavioural monitoring or targeted advertising (s.9(3)).
  - Penalties reach ₹200 crore for children's breaches and ₹250 crore for security failures.
  - [S1] T1, [S2] T1, [S5] T2
- **When:** the Act's sections 3–17 and Rules 3, 5–16 start **18 months after 13/14 Nov 2025 → 13/14 May 2027**. [S3] [S4] T2, [S2] T1
- **What this app touches:**

  | Version | Data | Is it ours to process? | Lawful basis | Consent screen? |
  |---|---|---|---|---|
  | v1 | Game save (cash, items, progress) | On-device only; not personal data | — | No |
  | v1 | Android Auto Backup copy | Lives in the player's own Google Drive; we can't access it [S30] | — | No |
  | v1 | Stats or deal card the player shares | Goes only where the player sends it, via the share sheet | Player's own action | No — show "This opens WhatsApp/email to send to the developer; nothing is sent unless you press send" |
  | v1 | Feedback and support messages we receive (they carry the sender's email or phone) | Yes, personal data | s.7(a) — voluntarily provided for that purpose [S1] | No |
  | Ads | IP address, app interactions, diagnostics, ad ID, app set ID — collected automatically by the Google Mobile Ads SDK for advertising, analytics and fraud prevention [S18, updated 2026-11-18] | Yes — we put the SDK in the app | Consent (s.6) | **Yes** |
  | Purchases | Order data | Google Play processes it; we see order IDs | Contract via Play | No (Google's flow) |

- **Forces:**
  1. v1 needs no consent screen. It needs a privacy policy, a contact email and a 90-day response habit. **STANDARD.**
  2. Before ads:
     - Show a **neutral age screen** — year of birth, nothing pre-filled, no hint of a required age [S14].
     - **Under 18 or unknown age → never initialise the ads SDK.** Rewarded perks become in-game alternatives, e.g. one free "Periyappa's tip" a day.
     - **18+ →** at the first tap on "watch an ad", a short notice listing what the SDK collects, why, and that Google receives it. Buttons: *Allow & watch* or *Not now*; nothing pre-ticked.
     - Save a consent record on the phone (timestamp, notice version, choice).
     - Add *Settings → Ads & privacy* to withdraw, which stops the SDK.
     - Link the notice in Tamil and Hindi script as web pages on GitHub Pages. The browser renders Indic script, so libGDX doesn't have to.
  3. Even before May 2027, building it this way avoids rework. Until then the Act's duties aren't in force.
- **ADVICE (ADV-1):** whether a self-declared age screen is enough "due diligence" for keeping children out; our role alongside Google (sole or joint fiduciary); and the final notice wording. **When:** before ads go live if that's after 13 May 2027, or by 13 May 2027 if ads are already live. Not a v1 cost.

### L-3 · Age and Google's Families rules
- **What:**
  - Play: "if your app targets any users who are under the age of 21, you must consider if they would be considered children under their local laws". Ages 13–15 and 16–17 "may be considered to include children in some locales". [S14] T1
  - Apps whose audience includes children must meet the Families policy [S15] T1:
    - a neutral age screen for mixed audiences
    - ads to children or unknown-age users only from Families self-certified SDK versions
    - no transmitting the advertising ID (AAID) from children or users of unknown age
  - Selecting only "18 and over" lets you optionally switch on **Restrict Minor Access**, which blocks accounts Google thinks are minors from finding, installing or buying. [S14] T1
- **Applies because:** the brief said 13+ [IDEA §7], and in India 13–17 are children [S1].
- **Forces:**
  - Declare **"18 and over"** as the only target age group.
  - Leave Restrict Minor Access **off** unless you want zero teens; content can stay teen-safe. [assumption]
  - Keep the listing and icon adult-facing — no toddler styling. Play checks listings of non-child apps for unintended appeal [S15] T1.
  - The age screen in L-2 still keeps minors' data out of ads.
- **STANDARD.** The alternative (keep 13+) costs Families compliance plus DPDP parental consent for the same result: no ads for minors.

### L-4 · SDKs, tracking and the advertising ID
- **v1:**
  - No third-party SDK that sends data — libGDX only; no Play Services ads, billing or analytics.
  - Data safety answer: **no data collected or shared.** On-device processing needn't be disclosed, and transfers from a user-initiated action the user expects aren't "sharing". [S17] T1
  - Answer **No** to the advertising ID declaration, and check the merged manifest has no `AD_ID` permission. Ad SDKs merge it in automatically. [S19] T1
- **Ads version:**
  - The Google Mobile Ads SDK collects the items in L-2 automatically [S18] T1.
  - Personalised ads use the ad ID across apps — linking your data with other companies' apps for ads. [T4 — confirm in Google's ad-personalisation docs; it doesn't change the design, since we gate the SDK on consent and age anyway]
  - The ad ID is user-resettable and must not be linked to persistent identifiers without consent [S19] T1.
  - iOS App Tracking Transparency: not applicable — Android only. [T4]
- **Forces:**
  - Dependency hygiene in v1.
  - At Milestone 4, update Data safety from AdMob's own mapping table [S18].
  - Initialise the SDK only after the age screen and consent.
  - No ad mediation (each network adds another SDK and disclosure).
  - **STANDARD.**

### L-5 · Where ads may appear
- **What:**
  - Play's Better Ads Experiences policy forbids unexpected full-screen interstitials: at the beginning of a game level or content segment, or before the app's home screen loads.
  - Interstitials *are* allowed at natural breaks, such as after a score screen.
  - Rewarded ads the player opts into are outside this policy.
  - [S20] T1
- **Applies because:** IDEA plans an interstitial every 3 auctions and rewarded peek / instant-finish / reopen-haggle.
- **Forces:**
  - The only interstitial slot is **after the day-end kanakku screen**, and only for consenting adults.
  - Never at an auction start, a customer's arrival, or app launch.
  - Rewarded ads stay opt-in buttons. **STANDARD.**

### L-6 · AdMob account safety and content controls
- **Invalid traffic** [S36] T1, [S54] T2: clicking your own live ads is prohibited. A suspension usually stops serving for ~30 days; a repeat leads to the account being disabled.
- **Test ads** [S53] T2: use Google's test ad units or registered test devices for every tester build.
- **app-ads.txt** [S37] T1: AdMob checks it at your developer website's root, so the Play listing must name that website. `rameshkumark24.github.io` works for free.
- **Ad content controls:** you can block sensitive categories (up to 50) [S39] T1 and set a maximum ad content rating [S38] T1.
- **Forces:**
  - Test ads always, for all 14 testers.
  - app-ads.txt on GitHub Pages.
  - Block gambling and real-money-game categories. **UNVERIFIED** whether "Gambling & betting" is in your AdMob list — confirm in the console at Milestone 4.
  - Set max ad content rating "T". [assumption]
  - **STANDARD.**

### L-7 · Payment rules (when purchases ship)
- **What** [all T1]:
  - Play Billing is mandatory for digital items, and nothing may steer players to other payment methods [S22].
  - Odds disclosure applies only to random items sold for money — we sell none [S22].
  - **Billing Library ≥ 8** for new apps and updates since 31 Aug 2026 [S31].
  - **Acknowledge every purchase within 3 days**, or it's refunded and the entitlement revoked [S32].
  - Only grant items when a **PENDING** purchase becomes PURCHASED — UPI payments can wait [S32].
  - Service fee: **15% on the first $1M a year if enrolled in the 15% tier**. India is still on this structure; new tiers went live in the US/EEA/UK on 30 Jun 2026 and AU/JP on 30 Sep 2026 [S33].
  - Refunds: Google's policies apply, *and* you must set your own refund policy, honour it and keep it lawful [S34].
- **Subscriptions and auto-renew disclosure:** not applicable; none planned. Changes nothing.
- **Forces:**
  - Refund policy page.
  - Enrol in the 15% tier.
  - A billing checklist: acknowledge, pending purchases, restore at launch.
  - **STANDARD.**

### L-8 · Who is named as the seller
- **What:**
  - Every developer shows a developer name and email on Play.
  - **Merchant accounts** — any paid app or in-app purchase — **must show their full address** on Google Play, taken from the payments profile.
  - [S24] T1
- **Forces:** decide before purchases: your home address becomes public, you use a business address (a cost), or the game stays ads-only. A personal choice, not legal advice. **STANDARD.**

### L-9 · Consumer and e-commerce rules
- **What:**
  - The Consumer Protection (E-Commerce) Rules 2020 cover "all goods and services bought or sold over digital or electronic network **including digital products**".
  - Natural persons are excluded only when not doing commercial activity "on a regular or systematic basis".
  - **Sellers on a marketplace** (Rule 6) must:
    - not use unfair trade practices or fake reviews
    - refund if an item is defective or not as described
    - appoint a **grievance officer who acknowledges complaints within 48 hours and resolves them within 1 month**
    - give the marketplace their legal name, address, website, email, customer-care numbers, and GSTIN/PAN where applicable
    - have the platform display the grievance officer's **name, contact numbers and designation**, plus refund terms
  - [S11] T1 (gazette text, via a mirror; the official site didn't respond)
  - The Dark Patterns Guidelines 2023 (CCPA, 30 Nov 2023) bind platforms, advertisers and sellers. **13 patterns:** false urgency, basket sneaking, confirm shaming, forced action, subscription trap, bait and switch, drip pricing, disguised advertisement, nagging, trick question, SaaS billing, rogue malware [S12] T2; interface interference [T4].
  - Paid influencers must label posts "advertisement", "sponsored", "collaboration" or "paid promotion", prominently [S13] T2.
- **Applies because:** once we sell on Play, we are very likely a "seller on a marketplace". Dark patterns apply to anyone offering services in India; our design (IDEA §3.2 "Never" list) already complies. Influencer rules govern your marketing.
- **Forces:**
  - Before purchases: a grievance / contact page plus an in-app Help entry with the 48 h / 1-month promise.
  - Testers and friends must not post reviews posing as ordinary players; never trade rewards for ratings.
  - Influencer posts carry a disclosure.
- **ADVICE (ADV-3):** confirm seller-on-marketplace status, and whether a phone number must be published. **When:** before the first purchase goes live.

### L-10 · Tax, and what an invoice must carry
- **What** [S35] T1:
  - "App or game developers based in India must determine any tax obligation themselves and pay GST as applicable". Google collects GST only for developers *outside* India.
  - Indian developers also owe **GST under reverse charge on Google's service fee**.
  - Developers who must register give Google their GSTIN.
  - Play prices must include all taxes.
  - Selling *outside* India needs BillDesk merchant verification (RBI cross-border rules) by **15 Mar 2027**.
- **From memory, not checked:**
  - GST registration threshold for services is ₹20 lakh (₹10 lakh in special-category states). [T4 — **UNVERIFIED**, confirm with a CA or the CBIC site]
  - Buyers get Google's order receipt; you don't invoice Play buyers. [T4]
  - Ad income is taxable business income. [T4]
- **Forces:**
  - No purchases until a CA has confirmed your GST position.
  - Stay India-only, so no BillDesk step yet.
- **ADVICE (ADV-2):** CA. **When:** before the first purchase; and for income tax once ad payouts start. Paid from revenue.

### L-11 · Licences of everything we ship

| Item | Licence | What it requires of us | Source |
|---|---|---|---|
| libGDX 1.14.x | Apache-2.0 | Include the licence and any NOTICE in Credits | [S42] T1 |
| AndroidX / Gradle plugins | Apache-2.0 | Same | T4 |
| Google Mobile Ads SDK, Play Billing (later) | Google's SDK terms; free | Follow Google's policies | T4 |
| FreeType (only if we used gdx-freetype at runtime) | FreeType License / GPLv2 | A credit line in docs if FTL. **Avoided** by baking bitmap fonts at build time | T4 — I couldn't retrieve the FTL text; not design-critical once avoided |
| Noto Sans (OFL 1.1, no Reserved Font Name in header) | SIL OFL 1.1 | Fonts may be bundled in games and apps (FAQ 1.4); can't be sold alone. OFL text isn't required when bundled in a program, but we'll put it in Credits anyway | [S43] T1 |
| Kenney packs | CC0 | Nothing; credit optional | [S44] T1 |
| game-icons.net | CC BY 3.0 | Credit each author and game-icons.net | [S45] T1 |
| jsfxr (tool) | The Unlicense | Sounds you generate are yours | [S46] T1; ownership of output T4 |
| Music | Use CC0 only | Avoid CC BY-NC (ads/purchases make it commercial) and CC BY-SA (share-alike) | T4 — **STANDARD** |
| Copyleft | None in the stack | **No GPL/AGPL runtime dependencies**, ever. JUnit (EPL-2.0) is test-only and isn't shipped | T4 |

**Forces:** a Credits & licences section in Settings; build-time font baking. **STANDARD.**

### L-12 · Content rules that are law or policy
- **Play hate-speech policy** [S40] T1: no stereotypes about groups by caste, religion, ethnicity and other characteristics.
- **Play IP policy** [S41] T1: no real brands, film posters or celebrity likeness.
- **Indian criminal law on religious feelings, currency look-alikes and the State Emblem** [T4]. Already covered by IDEA's rules: no deities, no real coins or notes.
- **Changes nothing** beyond what IDEA §5.10 already says: human read-through plus validator word lists. **STANDARD.**

### L-13 · Anywhere else people could use it — only if distribution widens
- **EU/EEA:**
  - GDPR reaches non-EU businesses that *target* people in the EU (Art. 3(2)), shown by intent such as EU languages, currencies or marketing, or by monitoring behaviour there.
  - India-only Play distribution isn't targeting the EU. [S47] T1 link, read via T2 summaries
  - Expanding there brings Google's consent form (UMP), an EU representative (Art. 27 — a paid service), and children's consent ages 13–16.
- **UK:** the same pattern under UK GDPR.
- **US:**
  - COPPA (US children's privacy law) applies only to child-directed services or with actual knowledge of under-13s. [T4]
  - California CCPA thresholds (≈ $26.6M revenue or 100k consumers) aren't met. [T4]
- **Apple:** App Tracking Transparency and in-app account deletion don't apply — no iOS app and no accounts. [T4]
- **Forces:** nothing now. Re-run this section before adding any country outside India.

### L-14 · Pages the stores and the law expect

| Page | Needed? | When | What it must contain | Source |
|---|---|---|---|---|
| **Privacy policy** — public URL, linked in the app and in Play Console | **Yes** | **Before the closed test (v1)** | **v1:** title "Privacy Policy"; who you are; contact email; that the game collects no personal data and has no ads or analytics; saves stay on the phone; Android may back up saves to the player's own Google Drive (we can't access it); shares go only where the player sends them; what we do with emails and messages we receive and how long we keep them (12 months suggested); not designed for children; effective date and changes. **At Milestone 4 add:** AdMob data types and purposes; Google as recipient with a link to Google's policy; the age screen; consent and how to withdraw; resetting or deleting the ad ID; DPDP rights (access, correction, erasure, grievance, nominate) with a 90-day response; complaint route to the Data Protection Board; contact person | [S16] [S1] [S2] [S18] [S30] T1 |
| **Terms of use** | Not for v1 | Before purchases | Licence to play; virtual items have no cash value and can't be transferred or sold; purchases through Google Play; refunds per the refund policy; Indian law; contact | T4 — **STANDARD** |
| **Refund policy** | When purchases ship | Before purchases | Google's refund routes; what you refund (non-delivery, defects — Rule 6(3)); how to ask; timelines | [S34] [S11] T1 |
| **Contact / grievance** | v1: a contact email (shown on Play anyway) | v1, extended before purchases | Purchases: grievance officer name, designation, contact numbers, 48 h acknowledgement, 1-month resolution. From May 2027: DPDP contact person | [S24] [S11] [S1] T1 |
| **Cookie policy and consent** | No | — | Our static GitHub Pages site sets no cookies by default, and the app has no in-app web view (the policy opens in the browser). Re-check if you add analytics to the site | T4 |
| **Credits & licences** | Yes | v1 | Apache-2.0 notice, CC BY credits, OFL text | [S42]–[S46] T1 |
| **app-ads.txt** | When ads ship | Milestone 4 | Your AdMob publisher line; developer website named in the listing | [S37] T1 |
| **Account / data deletion URL** | No (no accounts) | — | — | T4 |

### Advice vs standard practice

| ID | Item | Type | When | Why it can or can't wait |
|---|---|---|---|---|
| ADV-1 | DPDP: age-assurance sufficiency, our role with Google, consent wording | ADVICE (privacy lawyer) | Before ads go live after 13 May 2027, or by then if ads are already live | v1 has no ads → not a launch cost |
| ADV-2 | GST and income tax | ADVICE (CA) | Before the first purchase; income tax once ad payouts start | No money → nothing to tax |
| ADV-3 | E-commerce seller status; grievance details | ADVICE (lawyer) | Before the first purchase | Ads-only doesn't sell anything |
| ADV-4 | Online gaming classification | ADVICE (lawyer) | Only if social games are notified for registration, a regulator notice arrives, or before selling progress items | Design avoids every money-game factor |
| ADV-5 | Trademark | ADVICE (IP lawyer) | Only if the free search (IP India e-register, classes 9 / 28 / 41) shows a similar mark — before the public listing | A search is free; advice only on a hit |
| — | Everything else in this file | **STANDARD** | As listed | — |

---

## 3. What would get this rejected or pulled — ranked by likelihood for this app

| # | Reason | Why it's likely *here* | Prevention | Source |
|---|---|---|---|---|
| 1 | **Production access refused after the closed test** | First app on a new personal account (F1); 14 testers is only 2 spare; Play asks about your testing and feedback | Keep ≥ 12 opted in for 14 straight days; daily nudge; log feedback; answer with specifics (what you changed) | [S25] T1, [S50] T3 |
| 2 | **Privacy policy missing, broken or inconsistent** | Easy to forget for a "collects nothing" app; it's still required | GitHub Pages URL in Play Console + in-app link; content per L-14; re-check after every SDK change | [S16] T1 |
| 3 | **Data safety / ad ID declaration doesn't match the build** | When AdMob arrives, `AD_ID` merges in silently and the declarations go stale | Update Data safety and the ad ID declaration in the same release as the SDK; inspect the merged manifest | [S17] [S19] T1 |
| 4 | **Target audience or appeal-to-children mismatch** | Cartoon shapes plus "13+" in India | Declare 18+; adult-facing listing | [S14] [S15] T1 |
| 5 | **Metadata violations** | The brief's keyword list is tempting to stuff; v1 lacks 4 of 5 cities | Title ≤ 30 chars; keywords in natural sentences; no "#1/best"; no emoji in title; screenshots only of v1 | [S23] T1 |
| 6 | **Ads policy breaches** | Interstitial at an auction start; testers clicking live ads | Day-end-only interstitials; test ads always | [S20] [S36] T1 |
| 7 | **Read as simulated gambling** | Mystery lots + bidding + reveal | Gunny-bag reveal (never slot visuals); banned-word validator; answer the rating questionnaire honestly (bidding isn't wagering) | [S21] T1; questionnaire wording T4 **UNVERIFIED** → read it in Play Console at submission |
| 8 | **IP / impersonation** | Vintage radios, film-style posters, clocks invite real designs | Generic shapes; no brand marks; original poster art | [S41] T1 |
| 9 | **Hate speech / stereotypes** | Regional customer types in Mumbai/Delhi | Behaviour-based personalities only; human read-through | [S40] T1 |
| 10 | **Technical gates** | Certain block if missed | Target API 36; libGDX 1.14.x natives; Billing ≥ 8 | [S26] [S27] [S31] T1 |
| 11 | **Crash on the reviewer's device** | Low-end and Android 16 paths are untested | Test on a 2–3 GB phone and on Android 16 (both navigation modes) | T4 |
| 12 | **Payments policy** | Only if someone adds an external payment link | Play Billing only | [S22] T1 |

---

## 4. Sources (all checked 2026-11-23)

| ID | Source | Its date | Tier |
|---|---|---|---|
| S1 | [DPDP Act 2023 — MeitY PDF](https://www.meity.gov.in/static/uploads/2024/06/2bf1f0e9f04e6fb4f8fef35e82c42aa5.pdf) (read: s.2(f), 5(3), 6(1)(4)(10), 7(a), 8(9)–(10), 9) | Act of 11 Aug 2023 | T1 |
| S2 | [PIB — "DPDP Rules, 2025 Notified"](https://static.pib.gov.in/WriteReadData/specificdocs/documents/2025/nov/doc20251117695301.pdf) | 17 Nov 2025 | T1 |
| S3 | [TeamLease RegTech — DPDP commencement (G.S.R. 843(E))](https://teamleaseregtech.com/updates/article/49364/meity-issued-a-notification-regarding-the-commencement-of-the-dpdp-act-2023/) | Nov 2025 | T2 |
| S4 | [AMS Shardul — enforcement dates](https://www.amsshardul.com/insight/enforcement-of-the-dpdp-act-and-notification-of-the-dpdp-rules/) | Nov 2025 | T2 |
| S5 | [LiveLaw — DPDP Rules notified](https://www.livelaw.in/amp/top-stories/digital-personal-data-protection-rules-2025-notified-309928) | Nov 2025 | T2 |
| S6 | [PIB — Online Gaming Act 2025 backgrounder](https://static.pib.gov.in/WriteReadData/specificdocs/documents/2025/aug/doc2025821618101.pdf) | Aug 2025 | T1 |
| S7 | [PIB — Online Gaming Rules 2026 backgrounder](https://static.pib.gov.in/WriteReadData/specificdocs/documents/2026/apr/doc2026430859401.pdf) | Apr 2026 | T1 |
| S8 | [Khaitan & Co — ERGO on the Online Gaming Rules 2026](https://www.khaitanco.com/sites/default/files/2026-04/29%20April%202026_ERGO_Online%20Gaming%20Rules_2026.pdf) | 29 Apr 2026 | T2 |
| S9 | [Mondaq — Online Gaming Act 2025](https://www.mondaq.com/india/gaming/1670332/online-gaming-and-betting-india-responds-the-promotion-and-regulation-of-online-gaming-act-2025) | Aug 2025 | T2 |
| S10 | [Legal500 — social gaming doesn't escape regulation](https://www.legal500.com/developments/thought-leadership/indias-online-money-gaming-ban-how-social-gaming-gains-traction-but-doesnt-escape-regulation/) | 2025 | T2 |
| S11 | [Consumer Protection (E-Commerce) Rules 2020 — gazette text (mirror)](https://taxguru.in/wp-content/uploads/2020/07/Consumer-Protection-E-Commerce-Rules-2020.pdf) | 23 Jul 2020 | T1 (text) |
| S12 | [Deloitte — Dark Patterns Guidelines 2023](https://www2.deloitte.com/in/en/pages/tax/articles/tax-alert-the-guidelines-for-prevention-and-regulation-of-dark-patterns-2023.html) · [Mondaq overview](https://www.mondaq.com/article/1844140/ccpas-guidelines-on-dark-patterns-an-overview) | Dec 2023 | T2 |
| S13 | [Khaitan — Endorsements Know-hows](https://khaitanco.com/sites/default/files/2023-02/Ergo_31012023.pdf) · [DoCA PIB release (T1, not opened)](https://consumeraffairs.nic.in/sites/default/files/PIB1892527.pdf) | 20–31 Jan 2023 | T2 |
| S14 | [Play Console — Manage target audience](https://support.google.com/googleplay/android-developer/answer/9867159?hl=en) | live page | T1 |
| S15 | [Play — Families policy](https://support.google.com/googleplay/android-developer/answer/9893335?hl=en) | live page | T1 |
| S16 | [Play — User Data policy (privacy policy)](https://support.google.com/googleplay/android-developer/answer/10144311?hl=en) | live page | T1 |
| S17 | [Play — Data safety section](https://support.google.com/googleplay/android-developer/answer/10787469?hl=en) | live page | T1 |
| S18 | [AdMob — Google Play data disclosure](https://developers.google.com/admob/android/privacy/play-data-disclosure?hl=en) | updated 18 Nov 2026 | T1 |
| S19 | [Play — Advertising ID](https://support.google.com/googleplay/android-developer/answer/6048248?hl=en) | live page | T1 |
| S20 | [Play — Better Ads Experiences](https://support.google.com/googleplay/android-developer/answer/12271244?hl=en) | live page | T1 |
| S21 | [Play — Real-money gambling, games and contests](https://support.google.com/googleplay/android-developer/answer/9877032?hl=en) | live page | T1 |
| S22 | [Play — Payments policy](https://support.google.com/googleplay/android-developer/answer/9858738?hl=en) | live page | T1 |
| S23 | [Play — Metadata policy](https://support.google.com/googleplay/android-developer/answer/9898842?hl=en) | live page | T1 |
| S24 | [Play — Developer account info shown on Play (merchant address)](https://support.google.com/googleplay/android-developer/answer/13634081?hl=en) | live page | T1 |
| S25 | [Play — Testing requirements for new personal accounts](https://support.google.com/googleplay/android-developer/answer/14151465?hl=en) | live page | T1 |
| S26 | [Android — Target API level requirements](https://developer.android.com/google/play/requirements/target-sdk) | live page | T1 |
| S27 | [Android — 16 KB page sizes](https://developer.android.com/guide/practices/page-sizes) + my check of libGDX natives from [Maven Central](https://repo1.maven.org/maven2/com/badlogicgames/gdx/gdx-platform/): 1.14.2 PT_LOAD align 0x4000 ✓, 1.12.1 0x1000 ✗ | checked 22 Nov 2026 | T1 |
| S28 | [libGDX CHANGES](https://raw.githubusercontent.com/libgdx/libgdx/master/CHANGES) ("Added support for predictive back gesture (requires android:enableOnBackInvokedCallback="true")") | live file | T1 |
| S29 | [Android 16 behaviour changes — predictive back](https://developer.android.com/about/versions/16/behavior-changes-16) | live page | T1 |
| S30 | [Android — Auto Backup](https://developer.android.com/identity/data/autobackup) (25 MB, free) | live page | T1 |
| S31 | [Play Billing Library deprecation FAQ](https://developer.android.com/google/play/billing/deprecation-faq) | live page | T1 |
| S32 | [Play Billing — integrate (acknowledge in 3 days, pending)](https://developer.android.com/google/play/billing/integrate) | live page | T1 |
| S33 | [Play — Service fees](https://support.google.com/googleplay/android-developer/answer/112622?hl=en) | live page | T1 |
| S34 | [Play Console — Orders and refunds](https://support.google.com/googleplay/android-developer/answer/2741495?hl=en) | live page | T1 |
| S35 | [Google Payments — Account verification, taxes and FIRC for sales in India](https://support.google.com/paymentscenter/answer/7421525?hl=en) | live page | T1 |
| S36 | [AdMob — Invalid activity / own ads](https://support.google.com/admob/answer/3342099?hl=en) | live page | T1 |
| S37 | [AdMob — app-ads.txt](https://support.google.com/admob/answer/9363762?hl=en) | live page | T1 |
| S38 | [AdMob — Targeting (TFCD, TFUA, max ad content rating)](https://developers.google.com/admob/android/targeting?hl=en) | live page | T1 |
| S39 | [AdMob — Blocking sensitive categories](https://support.google.com/admob/answer/3150235?hl=en) | live page | T1 |
| S40 | [Play — Hate speech](https://support.google.com/googleplay/android-developer/answer/9878810?hl=en) | live page | T1 |
| S41 | [Play — Intellectual property](https://support.google.com/googleplay/android-developer/answer/9888072?hl=en) | live page | T1 |
| S42 | [libGDX LICENSE (Apache-2.0)](https://raw.githubusercontent.com/libgdx/libgdx/master/LICENSE) | live file | T1 |
| S43 | [Noto OFL.txt](https://raw.githubusercontent.com/notofonts/latin-greek-cyrillic/main/OFL.txt) · [OFL FAQ](https://openfontlicense.org/ofl-faq/) | live | T1 |
| S44 | [Kenney — support / licence](https://kenney.nl/support) | live page | T1 |
| S45 | [game-icons.net — about (CC BY 3.0)](https://game-icons.net/about.html) | live page | T1 |
| S46 | [jsfxr repository (licence: Unlicense)](https://github.com/chr15m/jsfxr) | live | T1 |
| S47 | [EDPB Guidelines 3/2018 on territorial scope](https://edpb.europa.eu/sites/edpb/files/files/file1/edpb_guidelines_3_2018_territorial_scope_en.pdf) (read via T2 summaries) | v2.0, 12 Nov 2019 | T1 link / T2 read |
| S48 | [Playio — rewarded ad benchmarks 2026](https://blog.playio.co/rewarded-ad-benchmarks-2026) · [CAS.ai eCPM](https://cas.ai/?p=11066) | 2026 | T3 |
| S49 | [PhonePe — Indus Appstore developer platform](https://www.phonepe.com/press/phonepe-announces-the-launch-of-the-indus-appstore-developer-platform/) | Feb 2024 | T2 |
| S50 | [dev.to — closed testing rejected for engagement (2026)](https://dev.to/tizoc_araujo_3cd9fb67191f/google-play-closed-testing-rejected-for-insufficient-engagement-what-that-actually-means-in-2026-ion) | 2026 | T3 |
| S51 | [DT Next — gaming rules: registration not mandatory for most games](https://www.dtnext.in/news/national/biz-online-gaming-ld-rules) | Apr 2026 | T2 |
| S53 | [Google — The importance of test ads](https://blog.google/products/admob/the-importance-of-test-ads-in-your-app/) | — | T2 |
| S54 | [Google — Understanding suspensions due to invalid traffic](https://www.blog.google/products/admob/understanding-account-suspensions-due-invalid-traffic) | — | T2 |

## 5. Unverified items and what would confirm them

| Item | Current tier | Confirm by |
|---|---|---|
| Haggling fun, pacing, first-session churn, content exhaustion, art conversion, discovery (R1, R4–R6, R12–R14) | T4 | G1 playtest, closed-test stats, G3 numbers — the gates in IDEA §7 exist for this |
| India eCPM range (R3) | T3 | Your own AdMob reports after Milestone 4 |
| "Gambling & betting" blockable in AdMob (L-6) | T1 page, item not found | AdMob console → Blocking controls at Milestone 4 |
| GST threshold; invoices; ad-income tax (L-10) | T4 | A CA (ADV-2) before the first purchase |
| Self-declared age screen enough under DPDP Rule 10 (L-2) | T1 law, interpretation open | ADV-1 before ads after May 2027 |
| Rating questionnaire treats bidding as not gambling (§3 #7) | T4 | Read the questionnaire definitions in Play Console at submission |
| Interface interference as the 13th dark pattern (L-9) | T4 | The CCPA gazette notification (30 Nov 2023) |
| GitHub Pages sets no cookies (L-14) | T4 | Browser dev tools on the published privacy page |

## 6. Next

Competitors, in their own step. Then the business step acts on the **classes** above: STOPS IT rows are closed doors; COSTS rows need time or money planned; ROUTINE rows go on checklists.
