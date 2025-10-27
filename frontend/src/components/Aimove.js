export function Aimove(board){
    let availableMove=[];
    for(let i=0;i<3;i++){
        for (let j = 0; j <3; j++) {
            if(board[i][j]==""){
                availableMove.push([i,j]);
            }
        }
    }
    return availableMove[Math.floor(Math.random() * availableMove.length)];
}