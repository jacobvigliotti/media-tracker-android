# Week 12 Reflection

**Name:Jake Vigliotti**
**Date: 8-6-26**

---

## Commits This Week

**Link: https://github.com/jacobvigliotti/media-tracker-android/commits/week-12?since=2026-08-06&until=2026-08-06**

---

## Code Review

**Reviewed:** *Dylan Browne*
**Link to my review: https://github.com/DylanBrowneMetrostate/media-tracker-android/pull/23**

### What I Looked At

I reviewed Dylan's implementation of posting, editing, and deleting reviews. I verifed that the API calls matched the expected specification according to the API Reference doc. I also looked at how he went about handling various UI interactions such as dynamically showing or hiding buttons based on state and setting up confirmation dialogs for risky actions like deletes.

### What I Noticed

I noticed several areas where the implementation was adjusted to be more consistent with the API Reference doc. I noted some data types were corrected and a switch from using query parameters to the expected JSON request body. I also noticed that Review and ReviewPost were potentially redundant, as I could not identify a clear purpose for having both and they seemed to represent the same thing. I also noticed a separate character count variable was being maintained even though the count could be calculated directly with .length. Finally the @launch syntax caught my eye as I have not seen this before. I found documentation on this referring to returns and jumps but it didn't seem to make sense in this context as there was no label to return to.

### Comments I Left

I called out the API corrections as a good change because they aligned the implementation with the API specification, while also reminding to verify data types and request formats against the API documentation. I asked about the purpose of having both Review and ReviewPost classes to determine whether they were actually necessary. I also noted that adding delete confirmation was a good call, asked for clarification on the @launch syntax, and suggested removing the separate character count variable since the value's .length can be accessed directly when needed.

---

## One Thing I Understood More Deeply

Building the priorities feature gave me a deeper understanding of how draggable lists are implemented. I used the reorderable library and saw how the from and to indices determine exactly where an item is being moved, then removes the item from its original position and inserts it at the new position to create the reordered list. Each ReorderableItem uses a draggableHandle() so the user has a specific UI element they can interact with to initiate the drag. Once the list is reordered on the front end, it is passed to viewModel.updatePriorityOrder(), which maps each item's new position to an orderIndex and sends those updates through the repository to the API. This helped me understand how the drag interaction, list manipulation, ViewModel, repository, and API work together to make the new ordering persist beyond just the UI.

---

## One Thing I'm Still Confused About

One area I’m still somewhat confused about is how drag-and-drop is implemented directly in Compose. I initially tried using pointerInput and detectDragGestures, but I found it difficult to understand how to translate a gesture into actually moving an item within a LazyColumn. I started having to consider things like tracking the dragged item, interpreting the X/Y coordinates of the gesture, determining when an item had crossed another item’s position, and handling scrolling while the user continued dragging. After seeing how the reorderable library abstracts these details into concepts like from and to indices and a draggableHandle, I chose to use the library instead. I understand the higher-level implementation now, but if I had more time I would like to better understand what is happening underneath the library and how I could build the same drag-and-drop behavior directly with Compose.

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
