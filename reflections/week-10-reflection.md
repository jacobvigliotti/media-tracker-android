# Week 08 Reflection

**Name: Jake Vigliotti**
**Date: 07-23-2026**

---

## Commits This Week

**Link: https://github.com/jacobvigliotti/media-tracker-android/commits/week-10?since=2026-07-23&until=2026-07-23**

---

## Code Review

**Reviewed:** *Dylan Browne*
**Link to my review: https://github.com/DylanBrowneMetrostate/media-tracker-android/pull/21/**

### What I Looked At

I looked at the new data models, adjustments to the MediaDetailViewModel, UI changes in MediaDetailScreen, and additional API‑related classes. I focused especially on the Favorite data model, the logic around toggling the “in library” status in the ViewModel, how the UI triggers that logic, and the new HttpErrorCodeMessage class. My goal was to understand how these changes fit into the existing architecture and whether the new code aligned with expected behavior for the media detail flow.

### What I Noticed

One thing I noticed was that several fields in the Favorite data model were nullable, and I questioned whether that made sense given that some of those values should probably be required. Making everything nullable can hide potential errors and make the model harder to reason about. I also noticed the comment in the ViewModel about the toggle logic being incorrect, and I agreed that the expected behavior needed clarification—specifically, whether the “in library” button should truly toggle or simply set a fixed status. In the UI, I saw that the "want_to" value was passed directly from the composable, which differs from how I handled it in my own implementation. Finally, I noticed the HttpErrorCodeMessage class but didn’t see where it was being used, which raised a question about whether it was necessary or still in progress.

### Comments I Left

I left comments asking whether the nullable fields in the Favorite model were intentional or if some should be required. I also agreed with the ViewModel note that the toggle logic needed correction and shared how I approached the “in library” state in my own implementation. On the UI side, I commented on the placement of the "want_to" value and noted that putting it in the composable could be a flexible choice depending on future changes. Lastly, I asked about the purpose of the HttpErrorCodeMessage class since I didn’t see it referenced anywhere, suggesting that it might need integration or cleanup.

---

## One Thing I Understood More Deeply

One thing I understood more deeply today is how mobile design has to account for the realities of real-world network behavior, especially the fact that users constantly switch between wifi, cellular, and weak or unstable connections. That made it clearer why good mobile apps don’t wait for an API call to finish before updating the UI. It’s better to let the interface react immediately to the user’s action and then handle the network request in the background, because the user shouldn’t feel stuck or uncertain just because the connection is slow. I also connected this idea to how powerful composables and StateFlow really are: when the underlying state changes, the UI updates automatically without me having to manually poll or refresh anything. I incorporated this into my own design by updating the UI state immediately on the button press before initiating the API call. This ensures instance user feedback while still ensuring the backend request is handled. If I were to mature this further I would want to account for the possibility of an error after the UI is updated and either revert the UI update or display an error.

---

## One Thing I'm Still Confused About

One thing I still don’t fully understand is why some APIs choose path parameters while others use a request body, and whether there’s a consistent standard behind those decisions or if it’s mostly arbitrary. In the example we looked at, both the library and favorite APIs used path parameters for their GET requests but switched to a request body for POST calls, and I’m not entirely sure why that pattern is used. I get that GET requests usually encode simple identifiers in the URL, while POST requests often send more structured data, but I’m still unsure how much of this is a strict convention versus just the preference of whoever designed the API. I’m trying to understand whether there are clear rules about when something belongs in the path, when it should be a query parameter, and when it should be part of the request body, or if it’s more about the API designer’s style and the needs of the specific endpoint.

---

## Anything Else *(optional)*

<!-- Did you help a pod mate work through something? Did you discover something cool or frustrating?
     Did something from a previous week finally click? This is a good place to put it. -->

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
