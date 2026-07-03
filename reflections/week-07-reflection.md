# Week 07 Reflection

**Name: Jake Vigliotti**
**Date: 07-02-2026**

---

## Commits This Week

**Link: https://github.com/jacobvigliotti/media-tracker-android/commits/week-07?since=2026-07-02&until=2026-07-02**

---

## Code Review

**Reviewed:** *Dylan Browne*
**Link to my review: https://github.com/DylanBrowneMetrostate/media-tracker-android/pull/17**

### What I Looked At

The purpose of this PR was primarily the implementation of the MediaDetailScreen. Only 3 files were relevant to this change: Media, which contained the model. MediaDetailScreen which contained the actual UI layout and implementation, and strings.xml which contained the string resource values referenced on the page. The only other files changed were the importing of some drawable XML files used for images.

### What I Noticed
I noticed that the Media model was updated to account for additional values that would be expected by the API such as description and counts of pages, seasons or episodes. This addition was important as these values will be used on the Media Detail page. Further I had noticed that he had good attention to detail when it came to matching the wireframe, details like changing the container color based on media type were things I had not considered. Finally, we both had similar approaches to dynamically handling displaying different attributes based on the media type. 




### Comments I Left

I left positive comments praising the attention to detail and following the wireframe. I asked some questions and proposed some alternate designs, such as avoiding nesting columns where not necessary. 

---

## One Thing I Understood More Deeply

Today's session was almost entirely dedicated to building a UI from scratch using a wireframe so I feel I am starting to understand the design elements and syntax around Jetpack Compose. It kind of feels like learning HTML/CSS from scratch so it is time-consuming, but a lot of the concepts are similar.

---

## One Thing I'm Still Confused About

Nothing was too confusing this week, I think my biggest concern is getting hung up on minor details and training to match the wireframe exactly which slowed me down this week. It was good to hear clarification that it doesn't need to be 100% accurate as long as we get the core functionality down. 

---

## Anything Else *(optional)*

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
