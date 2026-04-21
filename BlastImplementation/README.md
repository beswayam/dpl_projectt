# EzBlast

Repository for the code of the software application: EzBlast. 
EzBlast was developed for the course Software Engineering (INF32306) of Wageningen University.

Briefly, EzBlast is a bioinformatic application that can perform BLASTN and BLASTP searches to identify nucleotide 
and protein sequences that are similar to the query sequence. Moreover, the programme can compute basic statistics
of nucleotide and protein sequences. EzBLAST provides a more user-friendly tool compared to the [NCBI BLAST webpages]
(https://blast.ncbi.nlm.nih.gov/Blast.cgi).

The Class MainGui allows the user to navigate to and use all functionalities of the EzBLAST application.

* To perform BLASTP: press the BLASTP button.
* To perform BLASTN: press the BLASTN button.
* To calculate the statistics: press the File statistics button.
* To view the output of old BLASTP or BLASTN files: press the Upload .tsv file button. 
* Each section has a help button that explains how to use the tool.

## Repository overview
The repository contains six folders:
* `src/` - Contains all the Java scripts of the application.
* `tools/` - Contains the ssearch36 tool, which is an alternative to BLAST using a custom database.
* `referenced_libraries` - Contains all JAR files needed to run the UniProt SwissProt BLAST from class BlastpSearch.
* `project_data` - Contains FASTA files used in some of the test classes.
* `documentation` - Contains use case diagrams, user stories, class diagrams, sequence diagrams and acceptance tests.
* `.settings` - Configuration files for Eclipse.

Where `src/` contains:
* `/gui/` - Contains all GUI classes.
* `/interfaces/` - Contains all interfaces.
* `/tests/` - Contains all test classes.
* `/utilities/` - Contains all utility classes.

**Note**: The gui and utility classes have Javadoc annotation regarding their functionality.

And `documentation/` contains:
* `/Class diagram/` - Contains all class diagrams made during the project.
* `/Sequence diagram/` - Contains all sequence diagrams made during the project.
* `/Use case diagram/` - Contains all use case diagrams, user stories, use case descriptions, and acceptance test.

Where `/Use case diagram/` contains:
* `useCase_acceptanceTests` - Contains all acceptance tests of the user stories. 
* `useCase_descriptions` - Contains all use case descriptions.
* `useCase_diagrams` - Contains all use case diagrams.
* `useCase_progressPoints` - Contains tasks in MOSCOW style for each iteration.
* `useCase_userStories` -  Contains all user stories.

## Requirements
* [JDK](https://jdk.java.net/21/) - Used version: 21.

## Usage
1. Clone this GIT repository.
2. Open the repository in a Java compatible IDE.
3. Run class MainGui in the gui package in the `src` folder.
4. Navigate from the MainGui to the functionality that you want to use.

See the Javadoc of the classes in the gui package, the user stories, and the acceptance tests
for a detailed description about the functionality that the programme offers.

## Support
For questions, reach out to any of the authors:

[chris.ambagtsheer@wur.nl](mailto:chris.ambagtsheer@wur.nl), [loran.wijga@wur.nl](mailto:loran.wijga@wur.nl), 
[milan.vissers@wur.nl](mailto:milan.vissers@wur.nl), [nick.phoonleiyung@wur.nl](mailto:nick.phoonleiyung@wur.nl),
[rick.markus@wur.nl](mailto:rick.markus@wur.nl), [shreyas.bapat@wur.nl](mailto:shreyas.bapat@wur.nl),
[swayam.belokar@wur.nl](mailto:swayam.belokar@wur.nl) or [wouter.driessen@wur.nl](mailto:wouter.driessen@wur.nl). 

## Authors
* Chris Ambagtsheer, Loran Wijga, Milan Vissers, Nick Phoon, Rick Markus, Shreyas Bapat,
Swayam Belokar, Wouter Driessen

## License
This project is licensed under a [MIT license](https://opensource.org/license/mit).
