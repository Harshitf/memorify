import {createBrowserRouter, RouterProvider} from "react-router-dom";
import WhatsappHome from "./components/WhatsappHome.jsx";
import App from "./pages/App.jsx";
import TicTacToe from "./pages/TicTacToe.jsx";
import Sudoku from "./pages/Sudoku.jsx";

export default function Routes() {
    const route = createBrowserRouter([
        {
            path: '/',
            element: <WhatsappHome/>,
        },
        {
            path: "/word-meaning-home",
            element: <App/>
        }, {
            path: "/tic-tac-toe",
            element: <TicTacToe/>
        },
        {
            path: "/sudoku",
            element: <Sudoku/>
        }
    ]);
    return <RouterProvider router={route}/>

}