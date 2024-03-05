import { Routes, Route } from 'react-router-dom';
import Signup from './page/signup';
import Signin from './page/signin';
import Main from './page/main';

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<Main />} />
      <Route path="/signup" element={<Signup />} />
      <Route path="/signin" element={<Signin />} />
    </Routes>
  );
};

export default App;
