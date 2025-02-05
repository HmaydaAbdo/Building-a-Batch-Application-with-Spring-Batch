
# Building a Batch Application with Spring Batch

## Introduction : 
In this course, we will introduce the fundamental concepts of batch processing and cover the main features of Spring Batch. You will build a complete batch application with Spring Batch and Spring Boot and learn how to implement robust and fault-tolerant batch solutions.

Spring Batch is a lightweight, comprehensive batch framework designed to enable the development of robust batch applications vital for the daily operations of enterprise systems.

## What we will as a demo ? 

- Build and run a fully functional batch application that generates billing reports for a fictional cell phone company.
- The application stores billing information in a relational database and generates a billing report. This application is based on Spring Boot and uses Spring Batch's features to create a robust batch processing system that is restartable and fault-tolerant.


![img.png](img.png)

The billing job is structured in the following steps:

- File preparation step: copies the file that contains the monthly usage for Spring Cellular's customers from a file server to a staging area.
- File ingestion step: ingests the file into a relational database table that contains the data used to generate the billing report.
- Report generation step: processes the billing information from the database table, and generates a flat file that contains data for the customers who have spent more than $150.00 USD.

## Spring Batch Overview
First of all we will describe what batch processing is, and the challenges that come with it. We also introduce the Spring Batch framework, explain its domain model, and its internal architecture
### Introduction to Batch Processing
Batch processing is a method of processing large volumes of data simultaneously, instead of processing them individually, in real time (in that case, we could talk about stream processing). This approach is widely used in many industries, including finance, manufacturing, and telecommunications. Batch processing is often used for tasks that require the processing of large amounts of data, such as payroll processing or billing, as well as tasks that require time-consuming calculations or analysis. Batch applications are ephemeral, which means that once they've completed, they end.

This type of processing comes with a number of challenges, including, but not limited to:

- Handling large amounts of data efficiently
- Tolerance to human errors and hardware deficiencies
- Scalability

When it's time to provide a batch-based application for processing large amounts of data in a structured way, Spring Batch provides a robust and efficient solution. So, what is Spring Batch exactly? How does it help address batch processing challenges? Let's find out!

### Spring Batch Framework
Spring Batch is a lightweight, comprehensive framework, designed to enable the development of robust batch applications that are vital for the daily operations of enterprise systems.

It provides all the necessary features that are essential for processing large volumes of data, including transaction management, job processing status, statistics, and fault-tolerance features. It also provides advanced scalability features that enable high-performance batch jobs through multi-threaded processing and data-partitioning techniques. You can use Spring Batch in both simple use cases (such as loading a file into a database), and complex, high-volume use cases (like moving data between databases, transforming it, and so on).

Spring Batch integrates seamlessly with other Spring technologies, making it an excellent choice for writing batch applications with Spring.

## Batch Domain Language
The key concepts of the Spring Batch domain model are represented in the following diagram:

![img_1.png](img_1.png)

* A Job is an entity that encapsulates an entire batch process, that runs from start to finish without interruption. A Job has one or more steps. A Step is a unit of work that can be a simple task (such as copying a file or creating an archive), or an item-oriented task (such as exporting records from a relational database table to a file), in which case, it would have an ItemReader, an ItemProcessor (which is optional), and an ItemWriter.
* A Job needs to be launched with a JobLauncher, and can be launched with a set of JobParameters. Execution metadata about the currently running Job is stored in a JobRepository.

## Batch Domain model 

* Spring Batch uses a robust and well-designed model for the batch processing domain. It provides a rich set of Java APIs with interfaces and classes that represent all of the key concepts of batch processing like Job, Step, JobLauncher, JobRepository, and more
* While the batch domain model can be implemented with any persistence technology (like a relational database, a non-relational database, a graph database, etc), Spring Batch provides a relational model of the batch domain concepts with metadata tables that closely match the classes and interfaces in the Java API.
* The following entity-relationship diagram presents the main metadata tables:


![img_2.png](img_2.png)

* __Job_Instance__: This table contains all information relevant to a job definition, such as the job name and its identification key.
* __Job_Execution__: This table holds all information relevant to the execution of a job, like the start time, end time, and status. Every time a job is run, a new row is inserted in this table.
* __Job_Execution_Context__: This table holds the execution context of a job. An execution context is a set of key/value pairs of runtime information that typically represents the state that must be retrieved after a failure.
* __Step_Execution__: This table holds all information relevant to the execution of a step, such as the start time, end time, item read count, and item write count. Every time a step is run, a new row is inserted in this table.
* __Step_Execution_Context__: This table holds the execution context of a step. This is similar to the table that holds the execution context of a job, but instead it stores the execution context of a step.
* __Job_Execution_Params__: This table contains the runtime parameters of a job execution.