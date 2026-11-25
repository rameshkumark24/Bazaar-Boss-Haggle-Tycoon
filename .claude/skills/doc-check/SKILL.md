---
description: Check docs/ against the code, every third phase
disable-model-invocation: true
---
Before the next phase, check the documents against what we have built.

FIRST TIME ONLY — skip this if I typed /doc-check. Otherwise, if you can
write files here, create .claude/skills/doc-check/SKILL.md: these four
lines first, starting on line 1 —
---
description: Check docs/ against the code, every third phase
disable-model-invocation: true
---
— then this whole prompt, word for word. Commit it. From now on I
type /doc-check.

Re-read every file in docs/ — PRD, TRD, LLD, APP-FLOW, DESIGN,
EDGE-CASES, MONETISATION, KNOWN ISSUES in BUILD-PLAN — and the code.
Then tell me:

1. WHAT WE BUILT THAT CONTRADICTS THEM
   Where does the code now disagree with the document? For each, say
   which one is right — sometimes the code learned something and the
   document should change; sometimes we drifted and the code is wrong.
   ⭐ Include THE DATA TABLE in docs/PRD.md and the assets table in
   docs/DESIGN.md: any field, SDK, web view or asset in the code that
   they do not list?

2. WHAT WE DECIDED BUT NEVER WROTE DOWN
   Decisions made in a build session that live nowhere. These are the
   ones that get silently reversed three phases later.

3. WHAT IS NOW WRONG OR DEAD
   Sections describing something we cut, renamed, or replaced.

4. WHAT WE HAVE LEARNED THAT CHANGES THE PLAN
   Anything that should reorder, add, or remove a phase in
   docs/BUILD-PLAN.md.

5. ⭐ CODE HEALTH — the code against docs/LLD.md
   Code in the wrong module or layer · dependencies pointing the wrong
   way · a domain rule copied outside the domain layer · duplicated
   code that belongs in shared · files and functions grown too big ·
   a hot path slower than LLD promised · a DONE phase with missing
   tests, or with a check nobody ever showed passing · dependencies
   with known vulnerabilities or far out of date.
   Each real problem becomes a refactor phase in BUILD-PLAN.md —
   never a drive-by change inside a feature phase.

Then update the documents. Show me the diff in decisions, not a
rewritten file.

If everything still matches, say so plainly and we move on —
"nothing to change" is a good result.
