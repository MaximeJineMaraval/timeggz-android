# Contributing

**Table of contents** 
- [Reporting bug](#reporting-bug)
- [Code contribution](#code-contribution)
- [Contributing acceptance criteria](#contributing-acceptance-criteria)
- [Commit message format](#commit-message-format)

## Reporting bug

The first way to contribute to a project is simply reporting a bug. If you find anything which is not working well or as expected you can [open an issue](https://github.com/MaximeJineMaraval/timeggz-android/issues/new/choose).

Before to open the issue, please check if there is one similar already opened. It will save us hours of work and it will allow us to answer you quickly with the desired hotfix or implementation.

> **NOTE:** If you have found and existing **closed** issue related with your bug, please refer it when you are opening your one. It is maybe a regression we didn't see. In this way you will help to go faster and to find a definitive solution to the recurrent bug.

When you are opening an issue, please be sure to report as much information as you can to allow us to replicate the bug and easily find the solution.

## Code contribution

If you want to directly fix a bug or implement a new feature... you are the best one ! :clap:
To propose any changes you have to submit us a [Pull Request](https://help.github.com/articles/about-pull-requests/)

Here is the workflow:

1. Fork this repository (as you don't have a direct write access to the main one).
2. Create your code, `commit` and `push the code` on your forked repository
3. Create a GitHub `Pull Request` to our **main** branch.

We will take the time to review your code, make some comments or asking information if needed. As you took time to help us, we will take in serious consideration what you are proposing.

## Contribution acceptance criteria

We love maintenable software and we are happy when someone else than us is able to take the code, **understand it** and be able to change it.
To reach this goal we fixed some rules in our team and we would love to go ahead in this way, even with the external contribution:

1. Be sure your code compile: no syntax error, no missing library, ...
2. Add comments in your code in order to let your contribution as understandable as possible.
3. Be sure your code is tested (unit or UI tests)
4. Be sure to respect the [project architecture and frameworks](https://github.com/MaximeJineMaraval/timeggz-android#architecture-and-frameworks)
5. Respect the [commit message format](#commit-message-format)
6. Be aware of your Pull Request status until it has been accepted and merged. Some modification requests can be asked after automatic check (Github checks, CI) or human review.

If you respect all these rules you will help us saving time and we will be able to check your pull request faster.

## Commit message format

Your commit message should be written following this format (the close section is optionnal): 

`[contribution type]: [commit message] close #[issue number]`

Example : 
`feat: add new section in the settings screen. close #12`

Here is the contribution type list and when to use it :

- feat: new features contribution
- fix: issue fixes contribution
- refactor: refactoring contribution
- test: adding/managing test contribution
- docs: adding/managing documentation contribution
- chore: configuration contribution
- style: style only contribution
