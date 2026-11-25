# Waitlist page — the one-week test

The page for the one-week test in [IDEA.md → Business verdict](../docs/IDEA.md#business-verdict), §7. Built 2026-11-24.

| File | What it is |
|---|---|
| `bazaar-boss/index.html` | The page. One file. It sets no cookies, has no trackers and loads nothing from other sites |
| `bazaar-boss/og.png` | The picture WhatsApp and Instagram show when someone shares the link |
| `og-card.html` | The source of `og.png`. Not published |

Try it now: open `bazaar-boss/index.html` in your browser. Add `?voice=ta` or `?voice=hi` to the link to open it in Tanglish or Hinglish. That's handy for Tamil and Hindi groups.

## Before you post (about 20 minutes, all yours)

**1. Check the lines.** Read the Tanglish lines in the haggle (tap through in Tanglish). The Hinglish lines need a native reader (open question 4) before you post them in Hindi-speaking groups.

**2. Make the Google Form** (your Google login; a separate free Gmail for the game is better — it keeps your personal address off public pages):
- Title: `Bazaar Boss: launch waitlist`
- Description: `One email when Bazaar Boss launches on Google Play, or one saying it won't. Then we delete this list. For players 18 and over.`
- Settings → Responses:
  - **Collect email addresses: Responder input.** Not "Verified", which forces a Google sign-in.
  - Send responders a copy: off. Allow response editing: off. **Limit to 1 response: off** (it also forces a sign-in).
- Questions, and nothing else (no name, phone or city):
  1. *Which voice would you play in?* Multiple choice: English / Tanglish / Hinglish. **Not required.**
  2. *I'm 18 or older.* One checkbox. **Required.**
- Settings → Presentation:
  - Confirmation message: `Thanks. You'll hear from us once, at launch.`
  - **View results summary: off.** If it's on, every respondent can read the others' email addresses.
  - **Show link to submit another response: off.** It cuts accidental duplicate sign-ups.
- Send → link icon → tick "Shorten URL" → copy the `forms.gle/…` link.

**3. Put two values into the page.** Near the bottom of `index.html`:
```js
var CONFIG = {
  formUrl: "https://forms.gle/…",        // the link from step 2
  contactEmail: "yourgame@gmail.com"    // the address people write to for removal
};
```
Or send me both, and I'll fill them in. Until `formUrl` is set, the button reads "Waitlist opens soon" and does nothing.

**4. Publish.** On github.com, open your `rameshkumark24.github.io` repository → **Add file → Upload files** → drag in the whole `bazaar-boss` folder → **Commit changes**. About a minute later it's live at **https://rameshkumark24.github.io/bazaar-boss/**.

**5. Check on your phone:**
- Open the link. The haggle should play through in all three voices.
- Tap **Join the waitlist**. Send one test entry, then delete it under Form → Responses.
- Paste the link into a WhatsApp chat with yourself. The preview should show the picture.

## During the test

Post per IDEA §7: one clip, plus one post in each of 3 communities. For the clip, screen-record the haggle in Tanglish and voice the seller's lines yourself.

**Day 7:** count Form → Responses, and note each post's views. Go at ≥ 1,600 sign-ups; stop below 370; in between, build only to G1.

## After the test

- The list is used for **one** email: the launch, or the news that the game isn't coming.
- **Delete the responses within 30 days of that email.** Note the date in your requests log (IDEA → Hardening decisions).
- Anyone who asks to be removed earlier: delete their row and reply. Aim for 7 days.

## Regenerating og.png

Render `og-card.html` at 1200×630 and save the result as `bazaar-boss/og.png`. For example, with Edge:
```
msedge --headless=new --window-size=1200,630 --screenshot=og.png og-card.html
```
