import { createRoot } from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import App from './App';
import './css/index.css';

const root = document.getElementById('root');
if (root) {
  const rootElement = createRoot(root);
  rootElement.render(
    <BrowserRouter>
      <App />
    </BrowserRouter>
  );
}
