import { useState } from "react"
import axios from 'axios';

export default function SendEmailComponent(){

    const [to, setTo] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append('to', to);

    try {
      await axios.post('http://localhost:8080/email/send', formData);
      console.log("Email sent successfully");
      setError('');
    } catch (error) {
      console.error('Error sending email:', error);
      setError('Error sending email. Please try again.');
    }
  };

  return (
    <div>
      <h2><b>Welcome to Mail Sender</b></h2>
    <form onSubmit={handleSubmit}>
      <label>
        To:
        <input type="text" value={to} onChange={(e) => setTo(e.target.value)} required />
      </label>
      <button type="submit">Send Email</button>
      {error && <p style={{ color: 'red' }}>{error}</p>}
    </form>
    </div>
  );
}