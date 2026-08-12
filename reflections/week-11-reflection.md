**Name: Jake Vigliotti**
**Date: 7-30-26**
**My assigned bonus feature: Priorities**

---

## Commits This Week

**Link: https://github.com/jacobvigliotti/media-tracker-android/commits/week-11/?since=2026-07-30&until=2026-07-30**

---

## Code Review

<!-- Code review continues as normal — same pod rotation, regardless of which bonus feature you or your pod mate are building. -->

**Reviewed: Dylan Browne**
**Link to my review: https://github.com/DylanBrowneMetrostate/media-tracker-android/pull/22/changes**

### What I Looked At

I reviewed Dylan's implementation of the reviews feature, focusing on updates to the data models, repository implementations, API service definitions, and several UI screens. My attention was on how new review related functionality was integrated, how models aligned with the backend API, and whether the UI logic remained consistent and maintainable. I also examined filtering logic in the Library screen, the new Review creation flow, and the structure of composables added to support rating and review input. Overall, I looked for correctness, clarity, and alignment with expected API behavior.

### What I Noticed

I noticed the use of nullable fields in models where the API appears to require non‑null values, such as mediaId and status in LibraryItem and Favorite. Making these nullable can introduce unnecessary null‑checks and potential runtime issues. I also saw places in the networking layer where error handling was incomplete—specifically missing handling for 401 and 500 responses when fetching review pages. In the Library screen, the filtering logic included repeated non‑null assertions (!!), which suggests either the model shouldn’t be nullable or the UI should explicitly handle null cases. On the positive side, I noticed thoughtful structuring of repository classes, good alignment between ReviewPage and existing pagination models, and clean, reusable composables like StarRatingRow.

### Comments I Left

My comments highlighted both strengths and areas for improvement. I called out good API alignment in the models and praised the addition of @Serializable where needed for Review and User interactions. I suggested making certain fields non‑nullable to better match API requirements and reduce unnecessary null‑handling. In the networking layer, I recommended adding proper error handling for common failure responses. I also questioned the need for multiple Retrofit instances given their similarity. In the UI, I encouraged revisiting the filtering logic to avoid repeated !! usage and acknowledged the clear thinking behind correcting the toggle behavior in MediaDetailViewModel. Finally, I left positive feedback on the reusable composable functions, noting my own usage of this pattern in my project.

---

## Bonus Feature Progress

**What's working:**

I created a dedicated Priorities screen and added a new navigation route separate from the Library screen, which now allows users to view priority items in isolation. The UI for the priorities list is partially implemented and renders using real data from the API. I wired the GET endpoint so the app successfully fetches the user’s priority items and displays them in the new screen. The screen layout, list rendering, and basic state handling are all functional, and navigation to and from the priorities screen works as expected.

**What's still stubbed, fake, or not started:**

The update priorities API call and UI is incomplete. Right now it just adds an item to the priorities list but does not pass any of the optional parameters as the UI is not configured to collect this information yet. Some of the UI buttons exist like the filter chips for priority, but they don’t trigger real API calls yet as I have not completed the ability to set this data when adding priority items. I also haven’t implemented the draggable components to the UI yet so I just have a static list for now but it does show items in the order they are added.

**What I'm blocked on, if anything:**

My main blocker is understanding how to implement the draggable list which I have only started looking at. I was struggling to figure this out using LazyColumn and pointerInput so I may look at using a library instead to avoid diving deep into gesture logic.

---

## One Thing I Understood More Deeply

Building this feature independently helped me understand how new screens and routes integrate into the existing navigation graph, especially since the screen wasn't a of the original codebase. I also understood and appreciated Kotlin's built-in null safety checks a bit more, using simple syntax to indicate when something can or cannot be null, and how that shows up in other functions such as using a safe call or Elvis operator to react to null values and avoid null exceptions.

---

## One Thing I'm Still Confused About

I’m still unsure about the best architectural pattern for features that overlap with existing data models but aren’t part of the original app design. For example, priorities share fields with Library items, but they behave differently and may require separate repositories or API services. I’m not fully confident when it’s appropriate to reuse existing models versus creating new ones, or when a feature deserves its own repository instead of extending an existing one. I'm generally able to find a way to make features work independently but am not always confident if it algins with best practice or common patterns.

---

## Anything Else *(optional)*


---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Concrete progress report (what's wired, what's not) plus specific, honest "Understood More Deeply" and "Still Confused" sections. | Present but vague — "I worked on my feature" with no specifics on what's actually working. | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match.
