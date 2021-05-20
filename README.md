# HadesQL

HadesQL is backend written using Ktor and GraphQL. It's purpose is to demonstrate basic usages of CRUD operations on InMemory database / H2 database while using GraphQL.
It can be used as standalone, or in conjunction with Hades backend which provides more functionality.

## Install

Clone the project and run it. Simple as that.

By default it uses H2 database while working with Exposed to store data, but if you prefer to use InMemory one then in framework package, under di, 
use `single<MainRepository> { InMemoryRepository() }` instead of `single<MainRepository> { DefaultMainRepository() }` in RepositoriesModule.

## Built with

- [Kotlin](https://kotlinlang.org/) - Kotlin is a cross-platform, statically typed, general-purpose programming language with type inference.
- [Ktor](https://ktor.io/) - Ktor is an asynchronous framework for creating microservices, web applications, etc.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
 - [Dependency Injection](https://developer.android.com/training/dependency-injection) - 
   - [Koin](https://insert-koin.io/) - A pragmatic lightweight dependency injection framework for Kotlin developers.
- [Exposed](https://github.com/JetBrains/Exposed) - Exposed is a lightweight SQL library on top of JDBC driver for Kotlin language.
- [Gson](https://ktor.io/docs/gson.html) - Content negotiation which provides Gson converter for handling JSON data.
- [KGraphQL](https://github.com/aPureBase/KGraphQL) - KGraphQL is a Kotlin implementation of GraphQL.

## Playground

Once HadesQL is running you can work with it using [Playground](http://localhost:8080/thoughts) (default port is 8080, change it according to yours).

HadesQL provides basic CRUD operations and we'll go over their usage bellow (*Playground will by itself provide autocomplete for methods and fields*).

#### Get thougts
In playground, to get all thoughts, write your query like so:
```
query {
  getThoughts {
    id, title, content, date
  }
}
```
This will query for all thoughts and each thought will have 4 items. If you only want, lets say title, then just keep title instead of id, title, content, date.

#### Get thought
In playground, to get single thought, write your query like so:
```
query {
  getThought(id: "your_thought_id") {
    content
  }
}
```
This query will fetch thought based on its ID, but will only fetch content for that thought. 
If you need more fields, such as title, expand the query to contain title and content

#### Create thought
In playground, to create new thought, write your mutation like so:
```
mutation createThought($draft: ThoughtDraft!) {
  create(draft: $draft) {
    title, content
  }
}
```
and on the bottom of the screen, click on `Query Variables` and in it add following:
```
{
  "draft": {
    "title": "thought_title",
    "content": "thought_content"
  }
}
```
In this case, to create thought we need two items: title and content. ID and date will be automatically created. 
That's why we store `Thought` (which has all 4 items) but to create thought we use `ThoughtDraft` (which has two items).

Mutation will will accept `draft` of type `ThoughtDraft` and it'll be passed into create method. 
Since our mutation has return value (in this case we return Thought), we return it but also only fetch title and content of newly created thought.

Draft in Query Variables is thought data, defining the values for the draft of thought we're creating, and that draft will be injected into createThought mutation (names must match)

#### Update thought
In playground, to update single thought, write your mutation like so:
```
mutation {
  updateThought(
    id: "your_thought_id"
    title: "thought_title"
    content: "thought_content"
  )
}
```
Our mutation has slighly different approach here (for learning purposes). Here we directly pass in variables we need in order to update single thought.
In this case we use ID to find thought if one exists, and then update it's title and content.
This is to show that we can pass in data classes, but also individual fields.

#### Delete thought
In playground, to delete single thought, write your mutation like so:
```
mutation {
  removeThought(id: "your_thought_id")
}
```
Simple mutation which calls corresponding method (*playground will have autocomplete for such things*) and we pass in ID of thought we want to delete.

## Contribute

If you want to contribute to this repository, you're always welcome!

## Contact

If you need any help, feel free to contact me: kenan.karic@outlook.com.

## License
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
