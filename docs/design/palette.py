"""Bazaar Boss palette (DESIGN.md §2.3): OKLCH roles -> gamut-mapped hex, and WCAG contrast checks.

Usage: python -I docs/design/palette.py [--json] [--java PATH]
  --json       also writes docs/design/palette.json: each role's hex (LLD L-8, read by the palette guard)
  --java PATH  also writes the game's Palette class to PATH (core.ui.style.Palette), from the same values
Change a colour here first, re-run, then copy the values into DESIGN.md. Never pick a colour by eye.
Conversion: Bjorn Ottosson's OKLab matrices. Out-of-gamut colours keep L and h and lose chroma.
"""
import math
import sys

sys.stdout.reconfigure(encoding="utf-8")


def oklch_to_linear(L, C, h):
    a, b = C * math.cos(math.radians(h)), C * math.sin(math.radians(h))
    l_ = L + 0.3963377774 * a + 0.2158037573 * b
    m_ = L - 0.1055613458 * a - 0.0638541728 * b
    s_ = L - 0.0894841775 * a - 1.2914855480 * b
    l, m, s = l_ ** 3, m_ ** 3, s_ ** 3
    return (4.0767416621 * l - 3.3077115913 * m + 0.2309699292 * s,
            -1.2684380046 * l + 2.6097574011 * m - 0.3413193965 * s,
            -0.0041960863 * l - 0.7034186147 * m + 1.7076147010 * s)


def in_gamut(rgb):
    return all(-1e-6 <= x <= 1 + 1e-6 for x in rgb)


def fit(L, C, h):
    """Largest chroma <= C that is inside sRGB."""
    if in_gamut(oklch_to_linear(L, C, h)):
        return C
    lo, hi = 0.0, C
    for _ in range(40):
        mid = (lo + hi) / 2
        lo, hi = (mid, hi) if in_gamut(oklch_to_linear(L, mid, h)) else (lo, mid)
    return lo


def encode(x):
    x = min(max(x, 0.0), 1.0)
    return 12.92 * x if x <= 0.0031308 else 1.055 * x ** (1 / 2.4) - 0.055


def hexval(L, C, h):
    return "#" + "".join(f"{round(encode(v) * 255):02x}" for v in oklch_to_linear(L, C, h))


def lum(L, C, h):
    r, g, b = (min(max(v, 0.0), 1.0) for v in oklch_to_linear(L, C, h))
    return 0.2126 * r + 0.7152 * g + 0.0722 * b


def contrast(c1, c2):
    a, b = lum(*c1), lum(*c2)
    return (max(a, b) + 0.05) / (min(a, b) + 0.05)


ROLES = {
    # UI roles (Direction A "Dusk stall", one fixed palette)
    "surface":               (0.22, 0.035, 258),
    "surface-raised":        (0.28, 0.035, 258),
    "surface-tonal":         (0.36, 0.035, 258),
    "surface-tonal-pressed": (0.42, 0.035, 258),
    "divider":               (0.38, 0.030, 258),
    "text":                  (0.96, 0.010, 85),
    "text-muted":            (0.80, 0.020, 258),
    "text-disabled":         (0.62, 0.020, 258),
    "accent":                (0.80, 0.160, 72),
    "accent-pressed":        (0.72, 0.150, 68),
    "on-accent":             (0.22, 0.035, 258),
    "profit":                (0.80, 0.140, 150),
    "loss":                  (0.72, 0.160, 28),
    # Scene roles: illustration only, never UI chrome
    "scene-awning":          (0.66, 0.150, 40),
    "scene-paper":           (0.93, 0.030, 85),
    "scene-ink":             (0.25, 0.030, 258),
    "scene-counter":         (0.44, 0.070, 55),
    "scene-counter-edge":    (0.52, 0.070, 55),
    "scene-brass":           (0.74, 0.120, 85),
    "skin-1": (0.82, 0.060, 62), "skin-2": (0.72, 0.075, 58), "skin-3": (0.64, 0.090, 55),
    "skin-4": (0.55, 0.085, 52), "skin-5": (0.45, 0.070, 48), "hair": (0.25, 0.020, 60),
}

PAIRS = [  # (foreground, background, minimum ratio)
    ("text", "surface", 4.5), ("text-muted", "surface", 4.5), ("text", "surface-raised", 4.5),
    ("text-muted", "surface-raised", 4.5), ("text", "surface-tonal", 4.5), ("text", "surface-tonal-pressed", 4.5),
    ("text-muted", "surface-tonal", 4.5), ("text-disabled", "surface-raised", 3.0), ("on-accent", "accent", 4.5),
    ("on-accent", "accent-pressed", 4.5), ("profit", "surface-raised", 4.5), ("loss", "surface-raised", 4.5),
    ("scene-ink", "scene-paper", 4.5), ("accent", "surface", 3.0), ("text-muted", "divider", 3.0),
]

fitted = {}
for role, (L, C, h) in ROLES.items():
    c = fit(L, C, h)
    fitted[role] = (L, c, h)
    print(f"{role:22} oklch({L:.2f} {c:.3f} {h:>3})  {hexval(L, c, h)}")
print()
print("contrast")
bad = 0
for fg, bg, need in PAIRS:
    r = contrast(fitted[fg], fitted[bg])
    bad += r < need
    print(f"  {fg:15} on {bg:22} {r:5.2f}:1  need {need}  {'ok' if r >= need else 'LOW'}")
print()
print("neutral ramp (hue 258):", " ".join(hexval(L, fit(L, 0.035, 258), 258) for L in [0.98, 0.92, 0.84, 0.72, 0.60, 0.48, 0.38, 0.28, 0.20]))
print("accent ramp  (hue 72): ", " ".join(hexval(L, fit(L, 0.16, 72), 72) for L in [0.95, 0.88, 0.80, 0.70, 0.60, 0.50, 0.42, 0.34, 0.26]))
if "--json" in sys.argv:
    import json, os
    out = os.path.join(os.path.dirname(os.path.abspath(__file__)), "palette.json")
    with open(out, "w", encoding="utf-8", newline="\n") as f:
        json.dump({role: hexval(*fitted[role]) for role in ROLES}, f, indent=2)
        f.write("\n")
    print("wrote", out)
if "--java" in sys.argv:
    path = sys.argv[sys.argv.index("--java") + 1]
    lines = [
        "package io.github.rameshkumark24.bazaarboss.core.ui.style;",
        "",
        "import com.badlogic.gdx.graphics.Color;",
        "",
        "/**",
        " * The colour roles of DESIGN.md §2.3. Generated by {@code python -I docs/design/palette.py --java},",
        " * from the same values as docs/design/palette.json; a guard test compares the two. Never edit by hand,",
        " * and never mutate a constant: copy it first ({@code new Color(Palette.TEXT)}).",
        " */",
        "public final class Palette {",
        "",
    ]
    for role in ROLES:
        name = role.upper().replace("-", "_")
        lines.append(f"    public static final Color {name} = rgb(0x{hexval(*fitted[role])[1:]}); // {role}")
    lines += [
        "",
        "    private Palette() {",
        "    }",
        "",
        "    private static Color rgb(int rgb) {",
        "        return new Color((rgb << 8) | 0xff);",
        "    }",
        "}",
        "",
    ]
    with open(path, "w", encoding="utf-8", newline="\n") as f:
        f.write("\n".join(lines))
    print("wrote", path)
sys.exit(1 if bad else 0)
