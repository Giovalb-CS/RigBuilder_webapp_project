const formFactorInput = document.getElementById('formFactorInput');
const selectedFormFactorsContainer = document.getElementById('selectedFormFactorsContainer');
const hiddenFormFactorField = document.getElementById('formFactor');
let selectedFormFactors = [];

// Aggiungi un form factor alla lista di form factors selezionate al momento della pressione di Invio
formFactorInput.addEventListener('keydown', function(event) {
    if (event.key === 'Enter') {
        event.preventDefault(); // Previene il comportamento predefinito di invio del form
        const selectedFormFactor = formFactorInput.value.trim();

        if (selectedFormFactor) {
            // Controlla se il form factor è già presente
            if (!selectedFormFactors.includes(selectedFormFactor)) {
                selectedFormFactors.push(selectedFormFactor); // Aggiungi solo se non è già presente
                updateSelectedFormFactorsDisplay(); // Aggiorna la visualizzazione dei form factor selezionati
                formFactorInput.value = ''; // Resetta l'input dopo la selezione
            } else {
                alert('Form Factor already selected.'); // Notifica che il form factor è già presente
            }
        }
    }
});

// Aggiungi un form factor alla lista di form factors selezionati tramite cambio del datalist
formFactorInput.addEventListener('change', function() {
    const selectedFormFactor = formFactorInput.value.trim();
    if (selectedFormFactor) {
        // Controlla se il form factor è già presente
        if (!selectedFormFactors.includes(selectedFormFactor)) {
            selectedFormFactors.push(selectedFormFactor); // Aggiungi solo se non è già presente
            updateSelectedFormFactorsDisplay(); // Aggiorna la visualizzazione dei form factor selezionati
            formFactorInput.value = ''; // Resetta l'input dopo la selezione solo se è stato aggiunto
        } else {
            alert('Form Factor already selected.'); // Notifica che il form factor è già presente
        }
    }
});

// Funzione per aggiornare la visualizzazione dei form factor selezionati
function updateSelectedFormFactorsDisplay() {
    selectedFormFactorsContainer.innerHTML = ''; // Pulisci il container
    selectedFormFactors.forEach(formFactor => {
        const formFactorElement = document.createElement('div');
        formFactorElement.classList.add('formFactor-tag');
        formFactorElement.textContent = formFactor;

        // Modifica l'icona di rimozione usando innerHTML per inserire il tag <i>
        const removeButton = document.createElement('button');
        removeButton.innerHTML = '<i class="fa fa-times-circle" aria-hidden="true"></i>';
        removeButton.addEventListener('click', function() {
            removeFormFactor(formFactor);
        });

        formFactorElement.appendChild(removeButton);
        selectedFormFactorsContainer.appendChild(formFactorElement);
    });

    // Aggiorna il campo nascosto con la lista di form factors concatenati
    hiddenFormFactorField.value = selectedFormFactors.join('/');
}

// Funzione per rimuovere un form factor
function removeFormFactor(formFactor) {
    selectedFormFactors = selectedFormFactors.filter(s => s !== formFactor);
    updateSelectedFormFactorsDisplay();
}

// Controllo che almeno un form factor sia selezionato prima di inviare il form
const form = document.querySelector('form');
form.addEventListener('submit', function(event) {
    if (selectedFormFactors.length === 0) {
        event.preventDefault(); // Impedisce l'invio del form
        alert('Please select at least one form factor.');
    }
});

// Aggiungi evento di keydown a livello di documento per evitare rimozione dei form factors
document.addEventListener('keydown', function(event) {
    if (event.key === 'Enter') {
        // Controlla se il focus è su un campo di input diverso da formFactorInput
        if (document.activeElement !== formFactorInput) {
            event.preventDefault(); // Previene il comportamento di invio se non ci si trova nel campo form factor
        }
    }
});

// Carica i form factor esistenti al caricamento della pagina
document.addEventListener('DOMContentLoaded', function () {
    const formFactorDataElement = document.getElementById('formFactorData');
    const existingFormFactors = formFactorDataElement.getAttribute('data-formFactors');

    loadExistingFormFactors(existingFormFactors);  // Carica i form factor esistenti
});

// Funzione per caricare i form factor esistenti
function loadExistingFormFactors(existingFormFactorString) {
    if (existingFormFactorString) {
        const formFactorArray = existingFormFactorString.split('/'); // Split della stringa dei form factor
        formFactorArray.forEach(formFactor => {
            if (formFactor.trim() && !selectedFormFactors.includes(formFactor.trim())) {
                selectedFormFactors.push(formFactor.trim()); // Aggiungi ogni form factor all'array se non è già presente
            }
        });
        updateSelectedFormFactorsDisplay(); // Aggiorna la visualizzazione dei form factor selezionati
    }
}
