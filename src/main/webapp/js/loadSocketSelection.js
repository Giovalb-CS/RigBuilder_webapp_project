const socketInput = document.getElementById('socketInput');
const selectedSocketsContainer = document.getElementById('selectedSocketsContainer');
const hiddenSocketField = document.getElementById('socket');
let selectedSockets = [];

// Funzione per caricare le socket esistenti dall'input nascosto
function loadExistingSockets(existingSocketString) {
    if (existingSocketString) {
        const socketsArray = existingSocketString.split('/'); // Split della stringa delle socket
        socketsArray.forEach(socket => {
            if (socket.trim() && !selectedSockets.includes(socket.trim())) {
                selectedSockets.push(socket.trim()); // Aggiungi ogni socket all'array se non è già presente
            }
        });
        updateSelectedSocketsDisplay(); // Aggiorna la visualizzazione delle socket selezionate
    }
}

// Aggiungi una socket alla lista di socket selezionate al momento della pressione di Invio
socketInput.addEventListener('keydown', function(event) {
    if (event.key === 'Enter') {
        event.preventDefault(); // Previene il comportamento predefinito di invio del form
        const selectedSocket = socketInput.value.trim();

        if (selectedSocket) {
            // Controlla se la socket è già presente
            if (!selectedSockets.includes(selectedSocket)) {
                selectedSockets.push(selectedSocket); // Aggiungi solo se non è già presente
                updateSelectedSocketsDisplay(); // Aggiorna la visualizzazione delle socket selezionate
                socketInput.value = ''; // Resetta l'input dopo la selezione
            } else {
                alert('Socket già selezionata.'); // Notifica che la socket è già presente
            }
        }
    }
});

// Aggiungi una socket alla lista di socket selezionate tramite cambio del datalist
socketInput.addEventListener('change', function() {
    const selectedSocket = socketInput.value.trim();
    if (selectedSocket) {
        // Controlla se la socket è già presente
        if (!selectedSockets.includes(selectedSocket)) {
            selectedSockets.push(selectedSocket); // Aggiungi solo se non è già presente
            updateSelectedSocketsDisplay(); // Aggiorna la visualizzazione delle socket selezionate
            socketInput.value = ''; // Resetta l'input dopo la selezione solo se è stato aggiunto
        } else {
            alert('Socket già selezionata.'); // Notifica che la socket è già presente
        }
    }
});

// Funzione per aggiornare la visualizzazione delle socket selezionate
function updateSelectedSocketsDisplay() {
    selectedSocketsContainer.innerHTML = ''; // Pulisci il container
    selectedSockets.forEach(socket => {
        const socketElement = document.createElement('div');
        socketElement.classList.add('socket-tag');
        socketElement.textContent = socket;

        // Modifica l'icona di rimozione usando innerHTML per inserire il tag <i>
        const removeButton = document.createElement('button');
        removeButton.innerHTML = '<i class="fa fa-times-circle" aria-hidden="true"></i>';
        removeButton.addEventListener('click', function() {
            removeSocket(socket);
        });

        socketElement.appendChild(removeButton);
        selectedSocketsContainer.appendChild(socketElement);
    });

    // Aggiorna il campo nascosto con la lista di socket concatenate
    hiddenSocketField.value = selectedSockets.join('/');
}

// Funzione per rimuovere una socket
function removeSocket(socket) {
    selectedSockets = selectedSockets.filter(s => s !== socket);
    updateSelectedSocketsDisplay();
}

// Controllo che almeno una socket sia selezionata prima di inviare il form
const form = document.querySelector('form');
form.addEventListener('submit', function(event) {
    if (selectedSockets.length === 0) {
        event.preventDefault(); // Impedisce l'invio del form
        alert('Please select at least one socket.');
    }
});

// Carica le socket esistenti al caricamento della pagina
document.addEventListener('DOMContentLoaded', function () {
    // Prendi la stringa delle socket dall'attributo data-sockets
    const socketDataElement = document.getElementById('socketData');
    const existingSockets = socketDataElement.getAttribute('data-sockets');

    loadExistingSockets(existingSockets);  // Carica le socket esistenti
});

// Aggiungi evento di keydown a livello di documento per evitare la rimozione delle socket
document.addEventListener('keydown', function(event) {
    if (event.key === 'Enter') {
        // Controlla se il focus è su un campo di input diverso da socketInput
        if (document.activeElement !== socketInput) {
            event.preventDefault(); // Previene il comportamento di invio se non ci si trova nel campo socket
        }
    }
});
