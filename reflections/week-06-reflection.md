# Week 06 Reflection

**Name: Jake Vigliotti**
**Date: 06-25-2026**

---

## Commits This Week

**Link: https://github.com/jacobvigliotti/media-tracker-android/commits/week-06?since=2026-06-25&until=2026-06-25**

---

## Code Review

**Reviewed:** *Dylan Browne*
**Link to my review: https://github.com/DylanBrowneMetrostate/media-tracker-android/pull/14**

### What I Looked At

Our group for the most part was in a similar state of struggling with the pace of the code changes and needing to copy over what the professor had uploaded, so my first observation was there was not many differences in our code this time around. For this reason, instead of pointing out differences in our code, I called out general questions I had on some of the changes for this week, and suggestions for where to look for continued troubleshooting of some issues we had. I called out the spot in the DefaultSearchRepository file where the API response body and headers were being extracted to be used whilst populating search results and determining if there are more results to page through.

### What I Noticed

I noticed the HttpLoggingInterceptor and the logging level set to BODY. This made me curious as to the effect of this and what options are available for Http logging using an interceptor. After further research, I understood that this by default sends logs to the default console, Logcat and BODY is the most verbose option for logging which makes sense based on the HTTP errors I have encountered so far are rather detailed.

### Comments I Left

I left a comment about an observation I had on the BottomNavBar file having a change to the "isSelected" object. Previously, it was handled in a loosely coupled, modular manner but was later updated to hardcode conditions for the SEARCH and SEARCH_RESULTS routes. I questioned if this design was best practice, and could potentially be refactored in the future.

---

## One Thing I Understood More Deeply

I am understanding the API patterns a bit better. After studying and mimicking some of the patterns for the register page, it made them a bit clearer for the search. I connected the ViewModel calling a Repository class for necessary CRUD operations and the Repository class using the APIService classes to execute the calls. One mistake that helped me learn was trying to build a request body for a GET request, where these APIs don't accept a body, just query parameters. A good reminder to thoroughly review the API spec first!

---

## One Thing I'm Still Confused About

While no specific topic confused me, this week's pace felt too fast and I'm worried if the pace will continue to increase. We were not given a whole lot of time to develop the entire search experience and API and it feels like every time we group as a class myself and others have to scramble to catch up as a lot of new concepts and code are now being introduced and may be dependent for the next phase. I'm not clear if the expectation is to struggle through each breakout and then use your code to fill in the gaps when we re-group? This week that looked a lot like just copying code to not fall behind which did not feel like a great way to learn.

---

## Anything Else *(optional)*

Just some feedback on these reflection docs. It feels very redundant to have to look through a PR, look at it, leave comments, link it here, and then also summarize what was looked at and what comments were left in the reflection document. The 3 questions themselves feel redunant as well, as the things I looked at, noticed, and commented on typically overlap. One larger summary of the class with specific bullets to cover would make more sense to my brain.

I don't feel like I have sufficient time to complete a thorough review and leave substantive feedback and do the reflection documents in 15 minutes, and my group is often staying well past scheduled class time to complete them. Providing more time or extending the due date to the following evening at least would be appreciated.

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
