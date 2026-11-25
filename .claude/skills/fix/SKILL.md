---
description: Fix review findings — failing test, fix, proof
disable-model-invocation: true
---
Fix these review findings and verify findings.

FIRST TIME ONLY — skip this if I typed /fix. Otherwise, if you can
write files here, create .claude/skills/fix/SKILL.md: these four
lines first, starting on line 1 —
---
description: Fix review findings — failing test, fix, proof
disable-model-invocation: true
---
— then this whole prompt, word for word. Commit it. From now on I
type /fix.

For each one, before you change anything:
· Tell me whether you agree it is a real bug. If you think a finding
  is wrong, say so and explain why — a finding that is not a bug gets
  an explanation, not a "fix".
· Say what the actual failure is: what input or sequence produces it,
  and what the user would see.

Then fix them, most severe first. ⭐ For each one, in this order:
1. REPRODUCE — a test that fails because of the bug. Show it failing.
   No test possible? The exact steps by hand, and what they show.
2. FIX the cause.
3. PROVE — the same test passing, and the full suite still green.
Then tell me:
· what changed
· ⭐ WHY THIS CLASS OF BUG HAPPENED — if the same mistake could exist
  elsewhere in the app, say where and check those places too. One bug
  is a bug; a pattern is a design problem.

RULES:
· Tests stay as strict as they are. If a test is wrong, say the
  test is wrong and explain — I decide whether it changes.
· Fix the cause you can see, not its symptom.
· Keep the diff to the fix. A large diff hides the change I need to
  review.
· Fix what breaks correctness or a DONE WHEN check. A cleanup or
  style suggestion is optional — list it, and change it only if I
  ask.
· If a fix reveals a design problem too big for a patch, STOP and tell
  me rather than building a workaround I will inherit.

At the end: what is still broken that we are choosing to live with?
Add each one to KNOWN ISSUES in docs/BUILD-PLAN.md, with why it can
wait. Commit the fixes on the phase branch.
