# Bazaar Boss: Haggle Tycoon — Monetisation

How the game makes money, what that costs, and what must be true before anyone is charged. Written 2026-11-25 from [PRD.md](PRD.md), [TRD.md](TRD.md), [COMPETITORS.md](COMPETITORS.md) and [RESEARCH.md](RESEARCH.md), starting from the Business verdict's ROI math in [IDEA.md](IDEA.md).

- **Labels, as in the verdict:** **SEEN** (a provider's own page, opened, linked in §10) · **DERIVED** (arithmetic shown) · **ASSUMED** (a guess, with what would confirm it).
- **Money:** rupees, at ₹92.93 per US$ (the verdict's B1).
- **Not tax or legal advice.** Where a professional is needed, the item names RESEARCH's ADV number.

---

## The decision

**Recommended: the game stays free, and one-time cosmetic purchases are its only money. They ship right after G3, before any ads. Ads are built only if installs pass about 3,000 a month.**

**Why:**
1. **Purchases earn about 4× what ads earn per install:** ₹0.77 against ₹0.20 in the likely case (verdict §4).
2. **Purchase money reaches your bank; ad money mostly doesn't.** Google Play pays out monthly once you're owed ₹100 (SEEN M5). AdMob holds everything until $100, about ₹9,293 (the verdict's B15). In the likely case ads earn ₹81 a month, so the first ad payout is about **115 months** away (verdict §4).
3. **Ads cost the most for the least.** They need the age screen, the consent flow, privacy policy v2, the Data safety change and a privacy lawyer by 13 May 2027 (ADV-1). They end "collects nothing", which the competitors can't say, all for ₹81 a month in the likely case.

**What it asks of you:** one paid answer from a CA before the first sale (ADV-2), and a lawyer's answer on seller status (ADV-3). That breaks your rule "a CA only once the game has made money". **Under that rule, the likely case never sells anything:** purchases wait for an ad payout that's about ten years away. **Decided 2026-11-25: yes** (PRD Q19).

**What would change my mind:**
- The CA says a sale needs GST registration and monthly returns, and you won't file them yourself → don't sell until installs pass about 1,870 a month, where paying someone to file is covered (§3).
- Installs pass about 3,000 a month → add the rewarded ads too (M4 as the PRD already writes it). At that reach they add about ₹4,650 a month, enough to pay for ADV-1.
- After 3 months of sales, fewer than 0.2% of installs buy (the verdict's worst) → cosmetics don't sell here. Consider model 3 (§2), which means changing DR36 first.
- The waitlist test says stop (under 370 sign-ups) → build no monetisation at all.

**Business or project:** at likely reach, **a project** (about ₹310 a month). At best-case reach, a small business (₹9,100 a month from purchases alone, ₹13,700 with ads added). The waitlist test and G3 say which; don't price it like a business before they do.

---

## 0. Where the verdict's numbers changed

The verdict was written on 2026-11-24. Since then:

| What | The verdict | Now | Effect |
|---|---|---|---|
| **When money reaches the bank** | AdMob's ~₹9,293 threshold (B15); Play's payout not considered | **Play pays monthly from ₹100** (SEEN M5; Play Console says US$1 for local-currency payouts, SEEN M6) | Purchases pay out even in the likely case; ads don't. This is what flips the order |
| **GST on a sale** | "15% to Google"; GST left to a CA | Google's pages: a developer based in India pays any GST on sales themselves, and **GST under reverse charge on Google's fee**; Google may withhold income-tax TDS and GST TCS from payouts (SEEN M2, M3) | If registered, a ₹149 sale leaves ₹103.92, not ₹126.65 (§3). Likely income per install falls from ₹0.77 to **₹0.63** |
| **Registration below ₹20 lakh** | The CGST Act's ₹20 lakh threshold (B18) | Notification 65/2017 exempts suppliers of **services through an e-commerce operator** from compulsory registration up to ₹20 lakh (SEEN M7). But reverse-charge liability may still require registration whatever the turnover (from memory, CGST Act s.24) | ADV-2's question becomes exact: "Do I need GST registration to sell in-app items through Google Play below ₹20 lakh, given reverse charge on Google's fee?" |
| **What advice costs** | "Paid from revenue" | One online lawyer session: ₹199–₹10,000 (a 2026 guide, M10, ASSUMED). GST returns, if outsourced: from ₹599 + GST to ₹999 a month (SEEN at three providers, M8, M9). Registering on the GST portal: free (M8) | Both money routes need paid advice before their first rupee (§2) |
| **Alternative billing in India** | Not considered | Play allows a second, non-Google payment system in India, with Google's fee reduced by 4% (SEEN M4) | Not used: it needs a payment processor, KYC and more compliance, to save about 4% of very little |
| Exchange rate, fees, eCPM, retention | B1–B13 | Unchanged (opened a day ago) | — |

---

## 1. What it costs to run

There is no server (TRD §2), so a player costs nothing to serve. "Users" below means players in a month.

| | 100 | 1,000 | 10,000 | Source |
|---|---|---|---|---|
| Hosting (none; GitHub Pages for the policy and the waitlist) | ₹0 | ₹0 | ₹0 | TRD §1.2: 100 GB a month, soft limit; we use ~0.6 GB |
| Database and storage (on the phone; backups in the player's own Drive) | ₹0 | ₹0 | ₹0 | TRD §4 |
| Push notifications | none | none | none | DR39: none in v1, never a push service |
| Email and SMS | ₹0 (Gmail); no SMS | ₹0 | ₹0 | Gmail's 500-a-day limit hits the launch email first (TRD §12) |
| AI inference | none | none | none | — |
| Crash reports | ₹0 (Android vitals) | ₹0 | ₹0 | TRD §10 |
| **Money per player per month** | **₹0.00** | **₹0.00** | **₹0.00** | DERIVED |
| The store's cut | 15% of each sale, not per player | | | Verdict B2 |
| **Your support time** (1 email per 50 players a month, 7 minutes each, TRD §12 [A]) | **14 minutes** | **2.3 hours** | **23 hours** | DERIVED |

**When a free player becomes genuinely expensive: never in money, but at about 4,650 players a month in your time.** At that size, support email takes half of your 5 hours a week. At about 9,300, it takes all of it. DERIVED: 21.7 h a month ÷ (7 min ÷ 60 ÷ 50).

That number shapes the model:
- It must not add support work it can't pay for. Subscriptions and ads both do: COMPETITORS finds reviewers saying "a third of reward ads don't pay out".
- It needs stock replies for the five most common reports (TRD §12).

**One-off costs**, whatever the model: the Play account ($25, already paid); the advice in §2; your time on the 3 build slices each money route needs (about 2.4 weeks, verdict §3).

---

## 2. The three models that fit

The income figures are the verdict's (§4), for 402 installs a month (likely) and 3,232 (best). Model 3's are ASSUMED.

### Model 1 · Free, plus one-time cosmetic purchases (recommended)
- **Free:** the whole game, forever: every haggle, auction, tier, item and future city.
- **Paid:** the Dealer Pack, ₹149 (a theme set and a stall nameboard style), and single themes, ₹49. One-time, non-random, cosmetic only (DR36). These prices were decided in IDEA §2.
- **Why a player would buy:** pride at the tier-up, when the stall has just become a shop. The theme shows on the stall and on the deal card that friends see on WhatsApp. Plus "support a one-person game" [A]. Nothing sold helps anyone play.
- **Income per install:** ₹0.77 likely, ₹2.81 best. If GST-registered, ₹0.63 and ₹2.31 (§3).
- **A month:** ₹310 likely (₹254 if registered), ₹9,082 best.
- **Before the first rupee:**
  - ADV-2 (a CA) and ADV-3 (a lawyer on seller status): ₹1,000–₹7,000 together [A] (M8, M10)
  - 3 build slices; your address shown on Play (accepted, H5-12)
  - the terms, refund and grievance pages
- **Pays back the advice:** in 3–23 months at likely reach, or 1–9 months at G3's 1,000 installs a month. DERIVED.
- **Keeps the promise:** yes. "Free, no forced ads" becomes "free, no ads", and Data safety stays "no data collected" (to confirm for purchase records at that release, Q18).

### Model 2 · Free, plus opt-in rewarded ads; cosmetics later (the current plan)
- **Free:** everything. A player sees an ad only when they tap for one.
- **Paid, with attention:** an extra peek, or reopening a haggle; at most 12 a day (DR33). The same cosmetics as model 1 follow once AdMob pays out.
- **What advertisers pay:** $0.63–$2.43 per 1,000 rewarded views in India (verdict B4, B5). Those are third-party figures; AdMob publishes none.
- **Why a player would watch:** a second look at a lot, or a second chance with a customer who walked.
- **Income per install:** ₹0.20 likely, ₹1.44 best, from ads alone.
- **A month:** ₹81 likely, earned but not paid out for about 115 months. ₹4,654 best, paid from about July 2027, then purchases, making ₹13,700 by November (verdict §4).
- **Before the first rupee:**
  - ADV-1, a privacy lawyer, by 13 May 2027 (CR26): ₹199–₹10,000 [A] (M10)
  - 3 build slices: the age screen, consent, Ads & privacy, Your data, privacy policy v2, the Tamil and Hindi notice pages, app-ads.txt, the Data safety change
  - the risk of an AdMob suspension (R11)
- **Pays back the advice:** never, at likely reach.
- **Keeps the promise:** "no forced ads" holds, but "collects nothing" ends. Ads are the competitors' top complaint (36 of 99 reviewers, COMPETITORS §3).

### Model 3 · The first arc free, then a one-time "full bazaar" unlock
- **Free:** the first arc, stall to shop (30–45 minutes, F7).
- **Paid:** everything after it (the showroom, more cities, restoration), as one unlock at ₹149–₹199 [A].
- **Why a player would buy:** after 45 minutes, they want the next market. It's the try-before-you-buy version of Dealer's Life's ₹249–₹499 premium games (C5, C6).
- **Income per install:** ₹1.27 likely, ₹3.04 best [A]: 20% of installs reach the shop and 5% of those pay; best, 30% and 8%.
- **A month:** ₹509 likely, ₹9,824 best [A].
- **Before the first rupee:** everything model 1 needs. Also content worth unlocking, which won't exist before G3; and changes to DR36 ("purchases are cosmetic only") and to the waitlist's promise of "Free".
- **Keeps the promise:** only partly: "free" becomes "free to start".

| | Model 1 | Model 2 | Model 3 |
|---|---|---|---|
| Likely, a month | **₹310** (₹254 if registered) | ₹81, unpaid for ~115 months | ₹509 [A] |
| Best, a month | ₹9,082 | ₹4,654 from ads; ₹13,700 once purchases follow | ₹9,824 [A] |
| Money reaches you | Monthly, from ₹100 owed | Likely never; best case from ≈ July 2027 | As model 1, once content exists after G3 |
| Paid advice before the first rupee | ADV-2, ADV-3 | ADV-1 | ADV-2, ADV-3 |
| "Free, nothing collected" | Kept | Lost | Partly kept |

**Not considered further:**
- **A subscription.** Nothing recurs to sell in an offline single-player game played about 3 days per install. India's recurring-payment rules add friction, and a "subscription trap" is one of the 13 banned dark patterns (CR5).
- **Paid up front at ₹99.** It needs about 119 sales a month and drops "free" (verdict §8).
- **Usage-based pricing.** There's no usage cost to pass on.
- **A paid tier for teams.** There are no teams.
- **Ads plus a "Remove ads" purchase.** Dropped in PRD §5: there's nothing forced to remove.

---

## 3. ⭐ The store rule

**This game sells digital goods only: cosmetic themes, used inside the game. So it must use Google Play Billing** (CR29; RESEARCH L-7). It sells no physical goods or real-world services, so nothing has to stay outside Play Billing. Ads aren't a purchase and need no billing. Nothing in the game or on its pages may point players to another way to pay (CR29).

**Google's cut:** 15% for developers enrolled in the 15% tier, on the first $1M a year (verdict B2). India isn't named in the 2026 fee changes (B3).

**Tax:** Google doesn't collect GST for a developer based in India; if you're registered, you do (M2, M3). Play prices must include all taxes (RESEARCH L-10).

**What reaches you from each price**, DERIVED:

| Price the player pays | Google's 15% | You get, if not registered for GST | GST inside the price, if registered (18% [A]) | You get, if registered |
|---|---|---|---|---|
| ₹49 | ₹7.35 | **₹41.65** (85%) | ₹7.47 | **₹34.18** (69.8%) |
| ₹99 | ₹14.85 | **₹84.15** | ₹15.10 | **₹69.05** |
| ₹149 | ₹22.35 | **₹126.65** | ₹22.73 | **₹103.92** (69.7%) |
| ₹199 | ₹29.85 | **₹169.15** | ₹30.36 | **₹138.79** |

- **Google's fee is taken on the full price** [A]: it doesn't collect the tax itself. Check this against the first earnings report.
- **Reverse charge.** If you're registered, you also pay GST on Google's fee (₹4.02 on a ₹149 sale, at 18% [A]), and claim it back against the GST you collect (ADV-2).
- **Withholding.** Google may withhold income-tax TDS and GST TCS from payouts "if any" (M3). They're credits against your own tax, not costs.
- **Income tax** on the year's profit, at your slab, isn't in the price.

**So ₹149 at 100% becomes ₹103.92, about 70%, if you have to register.** The prices still work:
- They sit above the competitors' cheapest items, ₹75 and ₹90 (C3, C4), and below what an Indian casual payer spends, about ₹279 (B5).
- Keep ₹149 and ₹49. Under ₹100 might sell more, but at about 2 buyers a month Play's price experiments (M11) can't show it. Pick the prices and hold them; revisit at 100 buyers.
- Play's rupee price range (M1) wasn't readable; that ₹49–₹199 sits inside it is ASSUMED. Play Console shows the range when a price is entered.

**If GST returns must be filed:** filing them yourself on the GST portal costs nothing but time [A]. Paying a filing service costs ₹599–₹999 a month plus GST (M8, M9). At ₹1,179 a month, that's covered from about 1,870 installs a month (₹1,179 ÷ ₹0.63). DERIVED.

---

## 4. The paywall decision

**There's no paywall on play.** Every haggle, item, tier and future city is free.

**What's for sale:** looks only. Stall themes and a nameboard style, shown on the stall and on the deal card.

**When the player meets it:**
- From the purchases release, the shop has a button on the stall and an entry in Settings.
- The game mentions it **once, ever**: a "See the shop" link on the first Tier-up card, the moment the stall becomes a shop. By then the player has played 30–45 minutes (F7), seen a dozen verdicts and earned a tier, so they've felt the value.
- Never during a haggle, an auction or the kanakku's numbers; never at launch; never as a pop-up; never with a timer, a discount or a "best value" badge (CR5).

---

## 5. What the free tier costs

**In money, nothing** (§1). With no server, a script can't run up a bill: there's no API to hammer, no account to farm, and no free trial to repeat.

**In time, the inbox** (§1). Its limit, generous to a person and useless to a script, is stock replies. A report that names no version gets the stock reply asking for it.

**The abuse that's left, and what stops it:**

| Abuse | Guard |
|---|---|
| Buy, use, then get a refund | Play reports the refund, and the item re-locks at the next launch (DR38) |
| A cracked APK unlocks the cosmetics | Accepted (H5-11): its users would never have paid |
| Fake sign-ups on the waitlist | Decided on unique emails (PRD §12.1) |
| *If ads ever ship:* bots or ad farming | At most 12 rewarded ads a day (DR33), a reward only for a completed ad (DR32), testers on test devices (TD-32). Twelve is generous to a person: the verdict expects a watcher to use about 2 a day |

---

## 6. The honest assessment

**Would a real person pay?** Some, but rarely. The verdict assumes 0.5% of installs ever buy (likely). That's below free-to-play's usual 2–5% (B9), because nothing sold helps anyone play. Cosmetics in a single-player offline game sell worse than boosters; that's the price of keeping the promise.

**What they'd compare the price to:**
- The free competitors' items, from ₹5 to ₹11,800 (C1, C3, C4). Those do something: boosters, gold, guarantees.
- Dealer's Life at ₹249–₹499 (C5, C6), which is a whole game.

A theme that does nothing competes with paying nothing. The honest pitch is "make your shop yours, and keep a one-person game going".

**Business or project?** **A project, at likely reach:** about ₹310 a month, 3% of your ₹10,000 bar. It becomes a small business only at best-case reach (₹9,100 a month from purchases, ₹13,700 with ads), which needs about 8× the likely installs.

The model can't create reach; installs are the deciding number (verdict §5). The model can only avoid wasting reach. So price it like a project:
- no fixed monthly costs
- no paid filing service
- no ads compliance until reach pays for it

Let the waitlist test and G3 promote it to a business.

---

## 7. What must be proven before anyone is charged

The three purchase slices (IDEA §3, H2) are **P1** billing, **P2** the shop and its catalogue, and **P3** the pages. Each check below is a test in one of them. Testing uses Play's license testers and test cards that always approve, always decline, or approve slowly (from memory: Play Billing's testing tools; confirm at P1).

| # | Must be proven | How | Slice |
|---|---|---|---|
| 1 | **Purchase:** granted once, acknowledged | A test purchase; the order shows as acknowledged in Play Console; acknowledged within 3 days (RESEARCH L-7) | P1 |
| 2 | **Restore** | Uninstall and reinstall; then a second phone on the same Google account | P1 |
| 3 | **Renewal** | Nothing renews: there are no subscriptions. If one is ever added, test renewal, grace periods and account holds first | — |
| 4 | **Cancel** | Back out of Play's sheet; the always-declines card. Nothing changes | P1 |
| 5 | **Refund** | Refund the order in Play Console (M14): re-locked at the next launch, and an equipped theme falls back to the default (EDGE-CASES MN-6) | P1 |
| 6 | **The same purchase delivered twice** | No webhooks: there's no server (TD-17). The purchase arrives from the purchase listener and again from the launch-time query; it's granted once, by product ID | P1 |
| 7 | **A payment that fails halfway** | Kill the game after "payment successful": granted at the next launch (MN-2). Slow card: "Payment pending", then granted (MN-5). The network drops mid-flow: nothing granted until Play confirms | P1 |
| 8 | A double tap on Buy | One purchase flow (MN-4) | P2 |
| 9 | Prices come only from Play | The price-literal test (TRD §4.11, DR37) | P2 |
| 10 | Play can't be reached | Airplane mode: "Can't reach Google Play", and owned themes still work | P2 |
| 11 | The Play account changes | Owned items follow the account now signed in (GT-2) | P2 |
| 12 | The pages exist before the first sale | Terms, refund policy and the grievance contact, reachable from Help (CR28, CR32) | P3 |

---

## 8. The refund terms

**In-app purchases are refunded by Google Play, not by us.**
- **Within 48 hours** of buying, the player asks Google directly: [Request a refund on Google Play](https://support.google.com/googleplay/answer/15574897?hl=en) (M12). Google decides, usually within 1–4 days.
- **After 48 hours**, Google sends the player to the developer (M13). We refund from Play Console's order management (M14), which also revokes the item. A refund can't be undone.

**Our terms, for the refund page** (decided 2026-11-25, PRD Q20):
- **Who:** anyone who bought from Bazaar Boss on Google Play.
- **Within 30 days, for any reason:** email the game's address with the order number from Google's receipt (it starts "GPA."). We refund through Play Console within 7 days, and the item is re-locked (DR38). The terms are generous because they cost almost nothing: few buyers, and items that are looks only.
- **At any time:** if an item didn't arrive or doesn't work and we can't fix it, it's refunded (the E-Commerce Rules 2020, RESEARCH L-9).
- **How the money goes back:** only through Google Play, to the way they paid. Never to a bank account or UPI ID sent to us.
- **How fast we answer:** acknowledged within 48 hours, resolved within a month (CR32).

Nothing else is sold, so there's nothing else to refund.

---

## 9. What this changed in the PRD and APP-FLOW

- **PRD:**
  - **F21 · The shop**, with acceptance criteria, so the build plan builds it.
  - **Q19**, purchases before ads, and **Q20**, the refund terms, each with its recommendation.
  - The shop's row in §6, and the purchases row in §5, now point to F21 and Q19.
  - A change-log line.
- **APP-FLOW:** the shop's entry points, back, states, and a fourth journey: the first purchase.
- **Q19 and Q20, decided 2026-11-25:** purchases follow G3, before any ads; ads only if installs pass about 3,000 a month; the advisers are paid once before the first sale. Applied to PRD §5, §8.3, §8.4, §10, §11 and §12.4, APP-FLOW §1.8, and the TRD (§1.2, §3, TD-33).

---

## 10. Sources

All opened 2026-11-25 through a search tool's reading of each page. Open a page again before a money decision rests on it. The B and C numbers are the verdict's (IDEA.md) and COMPETITORS.md's.

| ID | Source | What it gave |
|---|---|---|
| M1 | [Play Console Help — Supported locations and price ranges](https://support.google.com/googleplay/android-developer/table/3541286?hl=en) | Price ranges per currency. The rupee row didn't come through, so the range is ASSUMED |
| M2 | [Google payments center — Account verification, taxes, and FIRC for sales in India](https://support.google.com/paymentscenter/answer/7421525?hl=en) | India-based developers "must determine any tax obligation themselves and pay applicable GST under reverse charge on service fee charged by Google" |
| M3 | [Play Console Help — Tax rates and value-added tax](https://support.google.com/googleplay/android-developer/answer/138000?hl=en) | Google, as marketplace intermediary, withholds TDS and GST TCS "if any". It handles GST only for developers outside India |
| M4 | [Play Console Help — Billing requirements for developers serving users in India](https://support.google.com/googleplay/android-developer/answer/13306652?hl=en) | A second payment system is allowed in India, with the service fee reduced by 4% |
| M5 | [Google payments center — Merchant payout threshold](https://support.google.com/paymentscenter/answer/7163445?hl=en) | Minimum merchant payout: ₹100 |
| M6 | [Play Console Help — Order processing and payouts](https://support.google.com/googleplay/android-developer/answer/137997?hl=en) | US$1 minimum for local-currency payouts |
| M7 | [GST Council — Notification 65/2017-Central Tax](https://gstcouncil.gov.in/sites/default/files/2024-05/notfctn-65-central-tax-english.pdf) | Suppliers of services through an e-commerce operator are exempt from compulsory registration up to ₹20 lakh |
| M8 | [IndiaFilings — GST return filing pricing](https://www.indiafilings.com/gst-return-filing/pricing) | From ₹998 + GST a month. Registering on the government portal is free |
| M9 | [eZTax — GST filing plans](https://eztax.in/gst-filing-plan-pricing) · [LegalDev — GST return filing](https://legaldev.in/gst-return-filing) | ₹999 a month · ₹599 + GST |
| M10 | [SurveyHeart — Lawyer consultation fees in India, 2026](https://surveyheart.com/explore/lawyer-consultation-fees-india-2026) | ₹199–₹10,000 per online session. A guide, not a provider, so ASSUMED |
| M11 | [Play Console Help — Price experiments for one-time products](https://support.google.com/googleplay/android-developer/answer/13343030?hl=en) | Price experiments exist; they need enough buyers to show anything |
| M12 | [Google Play Help — Request a refund](https://support.google.com/googleplay/answer/15574897?hl=en) | Within 48 hours, ask Google; decisions take 1–4 days |
| M13 | [Google Play Help — Refund policies](https://support.google.com/googleplay/answer/15574908?hl=en) | After 48 hours, or for an in-app item that doesn't work, contact the developer |
| M14 | [Play Console Help — Manage orders and issue refunds](https://support.google.com/googleplay/android-developer/answer/2741495?hl=en) | Refunds from order management; they can't be reversed; partial refunds for in-app items |
