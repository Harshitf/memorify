import { Link } from 'react-router-dom';
import './NavBar.css';

function NavBar() {
    return (
        <nav className="navbar">
            <Link to="/word-meaning-home" >Word Meaning   </Link>
            <Link to="/tic-tac-toe">tic tac toe game </Link>
            <Link to="/sudoku">sudoku game </Link>
        </nav>
    );
}
 
export default NavBar;
