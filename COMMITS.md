## Formatting
These formatting rules are intended to make the git log easier to read
for reviewers.

1. First line of commit message should be limited to 50 characters.
   This is a short description of the change which will be displayed
   in oneline format of git log.  

2. Second line of the commit message must be blank.

3. Remaining text should be wrapped at 72 characters (except for URLs or
   other pre-formatted text that cannot wrap)

4. Paragraphs should be separated by blank lines.


## Content

### Subject Line

The subject line (first line of the message) should be a very short
description of what change is being made -- i.e. describe the feature
you are implementing or the bug you are fixing.

### Long Description

The long description after that should more completely describe the
work. The goal of this description is to explain the changes to another
developer in general -- and to anyone reviewing your change in
particular.  Think of it as a justification/defense of your changes.

This description and should answer the following three questions:

1. Why is this change necessary?
   * Tells reviewers what to expect in the commit.
   * This might restate the requirements in a CR or other ticket, but
     probably adds more technical detail.

2. How does this change address the issue? 
   * Describe at a hight level what was done to affect change
   * If the change is obvious, you might be able to omit this
     information. Consider whether another developer reviewing your
     work will immediately understand your solution.

3. What side effects does this change have?
   * List any effects this change might have which are not explictly
     called for in the requirements.  
   * Explain why those changes were necessary

### Ticket references

At the bottom of the commit message, include any outside references to
the CR/issue/problem/SR ticket which generated this change.  

If possible, include a full URL to the ticket.  If not, include both the
ticket number and the system from which it comes.


## Examples

### Bad commit message:

    Fix login bug

### Improved commit message:

    Redirect user to the requested page after login

    Users were being redirected to the home page after login, which is less
    useful than redirecting to the page they had originally requested before
    being redirected to the login form.

    * Store requested path in a session variable
    * Redirect to the stored location after successfully logging in the user
    
    HPQC CR: 000000 ZZZ-NNNNNNNNNYYYYMMDDHHSS
    ServiceNow PRB000000: https://lexmark.service-now.com/nav_to.do?uri=problem_task.do?sys_id=XXXXXXXXXXXXXXXXXXXXXXX

## References:

* [5 Useful Tips for a better commit message] (https://robots.thoughtbot.com/5-useful-tips-for-a-better-commit-message)
* [A note about git commit messages] (http://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.htm])


