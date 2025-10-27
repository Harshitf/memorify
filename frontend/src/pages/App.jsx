import { useState } from 'react'
import './App.css'
import TextArea from '../components/TextArea/TextArea.jsx'
import Form from '../components/Form/Form.jsx'
function App() {
  const [textAreaValue, setTextAreaValue] = useState(''); // State to manage TextArea value

  function handleFormCall(message) {
    setTextAreaValue(message);
  }

  return (
    <div>

      <Form onCall={handleFormCall} />
        <TextArea value={textAreaValue} />
    </div>
  );
}

export default App
