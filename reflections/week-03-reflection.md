# Week 03 Reflection

**Name: Jake Vigliotti**
**Date: 06-04-2026**

---

## Commits This Week

<!-- Paste a link to your commits for this week. The easiest way: go to your repo on GitHub,
     click "commits", and copy the URL after filtering by your name or branch. -->

**Link: https://github.com/jacobvigliotti/media-tracker-android/commits/week-03/?since=2026-06-04&until=2026-06-04**

---

## Code Review

<!-- Every week you leave a review on a pod mate's pull request. Fill in both parts below.
     Part 1 is the link — I will verify the review exists on GitHub.
     Part 2 is your written assessment — what you actually looked at and what you found. -->

**Reviewed:** *Dylan Browne*
**Link to my review: https://github.com/DylanBrowneMetrostate/media-tracker-android/pull/5**

### What I Looked At

<!-- Walk through the code you reviewed. What was the PR trying to do? Which files or
     functions did you focus on? -->

I called out a couple of things I did differently, both things I enjoyed that he did and things I would suggest doing differently.

### What I Noticed

<!-- Be specific. Did you spot a potential bug? A pattern that could cause problems? Something
     done well that you want to call out? "I looked at the ViewModel and everything seemed fine"
     is not specific enough. Name the thing you noticed and explain why it matters. -->

I noticed that he kept the "LaunchedEffect" code from the LoginScreen and I questioned if this is correct or necessary. It wasn't obvious what this code was doing on the Login page and if its needed elsewhere.

### Comments I Left

<!-- Briefly summarize the comments you left on the PR. If you left a positive comment,
     say what it was. If you left a suggestion, say what you suggested and why. -->

I left several comments, both positive and some critical on any pieces of code that caught my eye. Some were just stylistic comments and others were things that I thought were good, like adding TODO on incomplete functions and setting up the strings ahead of time.

---

## One Thing I Understood More Deeply

<!-- Be specific. Don't write "I learned about ViewModels." Write what specifically clicked —
     what was confusing before, what made it make sense, and how you'd explain it to someone else.
     There are no wrong answers here. -->

I understand more about ViewModels and form design and the various methods available for constructing UI elements. Additionally, I understood more about how the API comes into play when submitting something that needs to talk to the server like the register button.


---

## One Thing I'm Still Confused About

<!-- Be honest. This is the most useful part of the reflection for me — it tells me where to
     spend more time in class. You will not lose points for being confused. -->

I'm a little confused on the project file structure and where things are supposed to live. The loose coupling seems like good design but also makes it challenging for a beginner to know where various functions and logic are supposed to reside.

---

## Anything Else *(optional)*

<!-- Did you help a pod mate work through something? Did you discover something cool or frustrating?
     Did something from a previous week finally click? This is a good place to put it. -->

Our team started diving into how to make the API call work. We worked together on debugging various errors which was a good exercise in team work. We hit a wall at the end trying to get the serializer to properly convert to JSON, hopefully we will resolve this next week!

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
