# Week 08 Reflection

**Name: Jake Vigliotti**
**Date: 07-09-2026**

---

## Commits This Week

**Link: https://github.com/jacobvigliotti/media-tracker-android/commits/week-08/?since=2026-07-09&until=2026-07-09**

---

## Code Review

**Reviewed:** *Dylan Browne*
**Link to my review: https://github.com/DylanBrowneMetrostate/media-tracker-android/pull/19**

### What I Looked At

I reviewed several parts of the PR, mainly the repositories, the navigation graph, and the media‑detail flow. I focused on how API responses were handled, how Dylan fixed issues with storing and passing the token, how navigation arguments were defined, and how the ViewModel updated the detail screen.

### What I Noticed

I noticed that the fallback item in the media repository is a practical way to avoid crashes when the API returns a null body. I also saw that making the access token non‑nullable strengthens the login flow as it would return a null exception if this is not obtained properly which may improve debugging if errors with the token occurs again. The default value for the navigation argument caught my attention because the ViewModel already uses the same default, and I wondered whether both were needed. In the detail screen, I called out that calling both setMediaId and updateMediaDetail is unnecessary and should be done implicitly since the setting of a mediaId should always result in the screen refreshing/updating. Finally, the use of AndroidViewModel stood out because it was not immediately clear why the application context was required for repository creation.

### Comments I Left

The comments I left reflected these observations. I gave positive feedback on the fallback strategy, agreed that the non‑nullable token made sense, asked whether the navigation default was necessary, suggested combining setMediaId and updateMediaDetail, and mentioned my confusion about why AndroidViewModel was needed.

---

## One Thing I Understood More Deeply

I better understood how to consider and handle errors when working with APIs. It was helpful to see the API spec and know which status codes to look for and how to account for them in our design. I setup mine such that the ViewModel would return a null value for the Media object if the get request resulted in an error, I then handled the screen to account for receiving a null media object and showing an error screen. I can see how this would be increasingly important with more complex APIs that may have more potential status values to handle. 

---

## One Thing I'm Still Confused About

I struggled for some time with getting the token to pass through my new MediaDetailViewModel class. Initially it was configured to return a ViewModel but once I introduced an instance of DefaultMediaRepository, it was clear I needed to change to return a AndroidViewModel and pass a DefaultSessionRepository with a context value as was done with the SearchResultsModel. It took me a while to get this right and it's still not clear to me the significance of each of these pieces or how to make this easier when creating new classes in the future (SessionRepository, AndroidViewModel, context, etc.)

---

## Anything Else *(optional)*

Just some more feedback on the class: Instead of putting people on the spot to share during class, would it make sense for you to join everyone's breakout session for a lower pressure, individualized review of how everyone is doing? Then maybe you could identify good candidates to share when we re-join as a group. I had another professor use this method and I liked it better.

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
