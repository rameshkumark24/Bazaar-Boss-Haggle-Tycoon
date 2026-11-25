---
description: Build the next phase in docs/BUILD-PLAN.md
disable-model-invocation: true
---
Build the next phase of the app.

FIRST TIME ONLY — skip this if I typed /phase. Otherwise, if you can
write files here, create .claude/skills/phase/SKILL.md: these four
lines first, starting on line 1 —
---
description: Build the next phase in docs/BUILD-PLAN.md
disable-model-invocation: true
---
— then this whole prompt, word for word. Commit it. From now on I
type /phase.

BEFORE YOU WRITE ANY CODE:
1. Get your bearings: git status and the last few commits, then
   docs/BUILD-PLAN.md. Find the first phase not marked DONE.
   If it is IN PROGRESS, continue from its PROGRESS.
   If the last DONE phase is still on its own branch and not in
   main, stop and tell me — it is waiting for its review and merge.
   If no phase is left, stop and tell me: everything is DONE, and
   /audit full comes next, then the final check.
2. Work on a branch for this phase (phase-N-<name>), cut from an
   up-to-date main. Mark the phase IN PROGRESS.
3. Read what the phase lists under READS, EDGE CASES and TESTS —
   plus docs/PRD.md section 7 (domain rules), the parts of docs/TRD.md
   it touches (data model, ownership rules, API shapes), docs/LLD.md
   for the modules it touches, and docs/DESIGN.md for every screen it
   builds.
4. Tell me the contract in a few lines: the phase, its GOAL, what is
   in and out of scope, and its DONE WHEN checks. Check your plan
   against the docs — if the plan or a doc is now wrong or out of
   date, say so before building.
5. PLAN MODE: yes ⇒ show me the plan and wait for my approval.
   PLAN MODE: no ⇒ no approval needed. Either way, write the plan
   into the phase's PROGRESS before coding.

WHILE BUILDING:
· ⭐ After each item in BUILD: update PROGRESS (done · left) and
  commit on the phase branch. Write a decision into its doc the
  moment it is made. If this chat is compacted or closed, the branch
  and that file are where the next session resumes.
· Build what the phase lists, and only that. A bug or an idea
  outside its scope goes into KNOWN ISSUES or a new phase.
· ⭐ Follow docs/LLD.md: each piece in its module and layer,
  dependencies pointing one way, domain rules only in the domain
  layer. Search shared code before writing something new.
· Write the phase's TESTS as you build, not after. For any hot path,
  state its complexity at the numbers in TRD section 0.
· Every screen ships all five states: loading, error, empty (both
  kinds), offline, success. Not one of them is optional.
· Every query enforces ownership server-side, with the mechanism
  docs/TRD.md names.
· Every listener and subscription is cleaned up on unmount.
· Name any dependency you add and why — and say if it needs NATIVE
  code, because that ends OTA updates for that change.
· A new field, SDK or web view: add it to THE DATA TABLE in
  docs/PRD.md in this phase. A new image, font or icon: add it to the
  assets table in docs/DESIGN.md, with its licence.
· Every image that carries meaning has a label.

STOP AND ASK ME — instead of guessing or pushing on — when:
· something listed under ASK FIRST IF happens
· the phase needs work outside its scope, or a doc turns out wrong
· the same check has failed three times after three different fixes
· a DONE WHEN check cannot be met as written. I change checks;
  you never edit one to match what was built.

WHEN THE BUILD ITEMS ARE DONE — PROVE IT:
1. Run typecheck, lint and every test. Show the output.
2. Take DONE WHEN one check at a time: run it, and show the evidence
   it names — command output, the request and response, a screenshot.
3. Screens: run the app, screenshot every screen and state this
   phase built, and compare each with the visual checklist in
   docs/DESIGN.md. Fix what differs, then screenshot again.
4. A check you could not run is NOT VERIFIED — say so, and tell me
   exactly how to check it myself. It does not count as passed.
5. Every check passed ⇒ mark the phase DONE, rewrite CURRENT STATE,
   make sure each decision is in the doc it belongs to — PRD, TRD,
   LLD, APP-FLOW or DESIGN — and commit.
6. Report: each check as PASS, FAIL or NOT VERIFIED with its
   evidence · what changed · what to look at first · what worried
   you · real device or simulator only · what the next phase is.
   Then stop — the review happens in a fresh session.

FREE TOOLS FOR THIS STEP — use the ones that help, and ask me before
installing anything. ME means it needs my login or my phone: tell me
what to look up, and I will bring back the result.
· ⭐ Maestro (maestro.dev) — drives a flow through the app and takes a
  screenshot of each screen on the way. The CLI is free.
· A screenshot from the emulator or simulator (adb exec-out screencap -p
  > shot.png, xcrun simctl io booted screenshot shot.png) — free and
  built in.
