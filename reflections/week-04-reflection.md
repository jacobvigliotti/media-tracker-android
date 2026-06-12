# Week 04 Reflection

**Name: Jake Vigliotti**
**Date: 06-11-2026**

---

## Commits This Week

**Link: https://github.com/jacobvigliotti/media-tracker-android/commits/week-04/?since=2026-06-11&until=2026-06-11**

---

## Code Review

**Reviewed:** *Dylan Browne*
**Link to my review: https://github.com/DylanBrowneMetrostate/media-tracker-android/pull/7**

### What I Looked At

I primarily focused on the changes to the RegisterScreen and RegisterViewModel. Specifically the implementation of the fields on the screen and how they connected to the ViewModel. 

### What I Noticed

I noticed how loosely coupled the data, logic, and state is from the view and form between the ViewModel and Screen files. The changes to the Screen were almost all cosmetic in nature whereas the handling of the click, validation, and success or errors were all in the ViewModel file.

### Comments I Left

I left comments regarding some good practices I see such as preparing some of the classes to handle values from the API documentation or some smaller changes to the screens to better align with the wireframe. This shows good attention to detail and use of the resource provided for the class. Additionally, I left some critical feedback about the implementation of the register click handling function where the conditional logic felt a little difficult to read and inefficient. 

---

## One Thing I Understood More Deeply

This week I feel that I am understanding some syntax and Kotlin/Android quirks more deeply such as how StateFlows and Composable work and render data on screens. Things like the "it" keyword for single parameter lambdas are now making sense and I learned about some other cool Kotlin features such as the use of "when" vs an if/else or switch statement or class implementations can override operators like "!=".

---

## One Thing I'm Still Confused About

The "sealed class" used for the RegisterUIState class was not making sense to me. At a high level I could follow how it was connected to state and handling errors or success but the syntax was all new to me. Hearing it was similar to an enum sort of helped but I'm hoping to spend more time on this in a future session.

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
