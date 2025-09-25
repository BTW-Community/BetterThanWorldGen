Below is a practical recipe for building a modern, “multi-noise + splines → 3D density” terrain system that stays continuous and yields realistic mountains. It’s inspired by how current Minecraft does it (multi-noise driven parameters mapped via splines into a single 3D density function), while staying implementable for your engine.
High-level goals
- Keep everything continuous: every map you evaluate, every remap you apply, and every blend you do should be at least C1-continuous (no hard clamps, no piecewise linear kinks).
- Use 2D fields to decide parameters; use 3D noise to add structure. Don’t directly swap noise types or frequencies abruptly based on 2D fields—blend smoothly.
- Shape mountains with ridged/multifractal noise and domain warping rather than pure FBM.

Core pieces
- 2D driving fields F2D(x, z): continentalness C, erosion E, ridges R, weirdness W (and optionally temperature T, humidity H for biome selection).
- Splines S_p: continuous mappings from these fields to terrain parameters (height, thickness, amplitude, warp strength, roughness, etc.).
- 3D density function D(x, y, z): a signed “distance-like” value; D < 0 means “solid,” D > 0 means “air.”

Part 1 — Build smooth 2D parameter fields via splines
1. Generate low-frequency 2D base fields

- Use octaved 2D Perlin/Simplex/OpenSimplex for each conceptual driver:
    - C(x, z) = continentalness (very low frequency)
    - E(x, z) = erosion (low frequency)
    - R(x, z) = ridges/ridge-likeness (low-to-mid frequency)
    - W(x, z) = weirdness (controls secondary shapes)

- Domain warp these with even lower-frequency 2D vector noise for variety, but keep warp magnitudes small and continuous (e.g., 10–60 blocks).

1. Evaluate splines to get terrain parameters

- For each parameter p you want (baseHeight h0, terrain thickness t, roughness r, warp amount w, mountain amp m, valley depth v, etc.), define a continuous spline S_p over one or more inputs. There are two common patterns:
    - Separable additive: p = S_pC(C) + S_pE(E) + S_pR(R) + S_pW(W)
    - Low-dimensional joint splines: e.g., S_p(C, E) or S_p(C, R)

- Use cubic Hermite or Catmull–Rom splines with continuous tangents. Avoid piecewise linear to prevent kinks.

Part 2 — Convert parameters to a target height and a vertical shape Think in terms of a signed distance-like density. A simple, robust baseline:
- Target height h(x, z): the “mean surface” height at that column.
    - h = seaLevel + h0 + mountainShape(C, E, R, W)
    - mountainShape can be a spline of R and E (ridges and erosion) that increases h where ridges are strong and erosion is low.

- Thickness/steepness t(x, z): controls how quickly density transitions from solid to air around h.
    - Thinner t makes steeper cliffs; thicker t makes soft hills.

- Vertical base shape (no high-frequency detail yet):
    - D_base(x, y, z) = -(y - h) / t
    - Negative below h (solid), positive above (air), and continuous everywhere.

Optionally compress far below/above with smooth polynomials so extremes saturate smoothly, not abruptly:
- For y far below h, use a smooth cap like D_base ← D_base - s_curve(k*(h - y))
- For sky fade, do symmetrical soft capping.

Part 3 — Add 3D detail that respects y and parameters
1. Build a warped 3D domain

- Compute a 3D vector warp W3(x, y, z) from low-frequency 3D noise (keep magnitude small vs the feature size, e.g., 8–32 blocks):
    - p = (x, y, z) + w(x, z) * W3(x, y, z)

- Make w(x, z) come from a spline S_w(C, E, R, W). Keep w continuous and avoid sudden jumps.

1. Compose your 3D noise

- Base FBM: n_fbm(p): standard octaves of Perlin/Simplex
- Ridged FBM for mountains:
    - ridge(n) = 1 - |n|
    - n_ridge_fbm = sum_i ridge(n_i(p * f_i)) * a_i, with proper normalization

- Blend based on ridge-likeness (R) and/or “weirdness” (W):
    - sR = smoothstep(r0, r1, R) // continuous blend factor for ridgedness
    - n_mountain = mix(n_fbm, n_ridge_fbm, sR)

- Optional terraces:
    - terrace(n, k) = n - fract(n*k)/k smoothed by a softstep; or remap n via a smooth piecewise cubic with soft knees. Use sparingly and always with smoothing.

1. Make the 3D noise amplitude vary smoothly with height

- Let A_surface(x, z) = S_amp(C, E, R, W) be the near-surface amplitude.
- Fade detail out below and above:
    - s_y = smoothstep(-a, a, y - h) mirrored as needed, or use a bell curve centered at h.
    - A(x, y, z) = A_surface(x, z) * s_y

- Final detail contribution:
    - D_detail = A(x, y, z) * n_mountain(p)

- Avoid discontinuities by not abruptly scaling frequencies with y or 2D fields. Prefer domain warp or amplitude modulation. If you must vary frequency, vary in log-space and keep the range tight.

Part 4 — Combine and finalize density
- D(x, y, z) = D_base(x, y, z) + D_detail - sea_bias
- sea_bias is a small continuous offset that ensures oceans exist where C is low (can be a spline of C and E).
- For bedrock/sky falloffs, use soft transitions:
    - D ← soft_min(D, D_bedrock(y), k) with a smooth/soft-min, not a hard min.
    - D_bedrock(y) could be a steep negative curve near y=0, evaluated with s-curves.

Continuity checklist (why discontinuities happen and how to avoid them)
- Never use hard thresholds (if/else) to switch noise types or parameters. Always blend with smoothstep.
- Use at least C1 splines (Catmull–Rom or cubic Hermite). Specify tangents to avoid kinks at control points.
- If you modulate frequency by multiplying coordinates, do it gently and continuously. Large frequency jumps cause phase jumps that look like seams. Prefer domain warping for “changing detail scale.”
- Normalize and blend noises of similar ranges; otherwise, blending can cross zero in unexpected ways.
- Keep domain warp magnitude small compared to the feature size; too much warp can fold surfaces and create “overhang gaps” that look like discontinuities.
- Ensure coherent seeding across chunk borders (same permutation tables, consistent spatial coordinates). No per-chunk randomness that affects function values.
- When combining fields (soft min/max for caves/cliffs), use soft-min/soft-max instead of true min/max.

Making mountains look realistic
- Ridged multifractal:
    - ridge(n) = 1 - |n| (then square or exponentiate to sharpen: ridge(n)^γ, γ ∈ [1.5, 3])
    - Use FBM on ridged noise with decreasing amplitude and increasing frequency.

- Domain warping:
    - Warp the input coordinates with a lower-frequency vector noise. A small, smooth warp adds natural folds and asymmetry.

- Anisotropy and slope-aware shaping:
    - Estimate gradient magnitude |∇n| via small finite differences and boost noise where gradients are high to sharpen ridges:
        - n_sharp = mix(n, sign(n) * pow(abs(n), γ), s_grad) where s_grad = smoothstep(g0, g1, |∇n|)

- Erosion proxies (without simulation):
    - Use an “erosion” field E to reduce mountain amplitude and add valleys: m_eff = m * (1 - e_factor(E))
    - Add gentle terrace functions and valley “V-shapes” by subtracting a valley-shaped function near rivers (use a low-frequency river mask derived from a band-pass-filtered 2D noise).

- Multi-scale ridges:
    - Combine two ridged FBMs with slightly different lacunarities and warp them differently; blend with smoothstep for complex ridge networks.

Practical parameter tips
- Frequencies:
    - 2D drivers: very low (periods hundreds to thousands of blocks).
    - 3D detail: base octave period 32–64 blocks; 4–6 octaves; lacunarity ~2.0; gain 0.4–0.6.

- Warp:
    - 2D warp magnitude ~10–40; 3D warp magnitude ~8–24.

- Ridged shaping:
    - ridgeExponent γ ~ 1.8–2.6.

- Blends:
    - smoothstep thresholds should overlap generously so transitions span tens of blocks.

Debugging continuity
- Visualize each intermediate field (C, E, R, W; h; t; w3; A; D_base; D_detail).
- Temporarily disable warping, ridging, or terrace functions to isolate artifacts.
- Verify splines are C1 with reasonable tangents; test extremes of inputs.
- Ensure no per-chunk randomness enters density evaluation (coordinates must be absolute/world-space).

If you adopt this structure, you can “steer” terrain with the 2D fields and splines while the 3D noise adds continuous, height-aware details. Ridged FBM + domain warping, blended via smooth masks, will give you sharp, realistic mountain ranges without the “lumpy” look or seams.
