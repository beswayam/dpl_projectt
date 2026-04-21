# ASB diauxic growth

Repository for the code related to the analyses performed in the diauxic growth report for Advanced Systems Biology (SSB40306).

Briefly, the models investigate how a population of bacteria show diauxic growth due to the preferential uptake of substrates that give higher growth rates. 
The latter is known as carbon catabolite repression (CCR). The simulations were performed for batch conditions.

Four models were constructed:
1. A model without CCR.
2. A model with CCR.
3. A model with CRR and dilution due to growth.
4. Model 3 but with stochasticity for the substrate transporters.

## Repository overview
The repository contains four Jupyter notebooks:
* `Catabolite_repression_model1.ipynb` - The model and simulations of model 1.
* `Catabolite_repression_model2.ipynb` - The model and simulations of model 2.
* `Catabolite_repression_model3.ipynb` - The model and simulations of model 3.
* `Catabolite_repression_model4.ipynb` - The model and simulations of model 4.

## Requirements
* [Python](https://www.python.org/) - Used version: `3.12.13` with:
    * [Numpy](https://numpy.org/) - Used version: `2.0.2` 
    * [Matplotlib](https://matplotlib.org/) - Used version: `3.10.0`
    * [Scipy](https://scipy.org/) - Used version: `1.16.3`
    * [Google](https://developers.google.com/workspace/docs/api/quickstart/python) - Used version: `3.0.0`

## Usage
1. Clone this GIT repository or download the Jupyter notebooks.
2. Open the notebooks in [Google Colab](https://colab.research.google.com/) or [Anaconda](https://www.anaconda.com/download)
3. Execute the notebooks

Note: The path were the plots are stored need to be adapted to your local path.


## Support
For questions, reach out to [rick.markus@wur.nl](mailto:rick.markus@wur.nl)

## Authors
* Rick Markus

## License
This project is licensed under a [Creative Commons Attribution 4.0 International license](https://creativecommons.org/licenses/by/4.0/deed.en).
