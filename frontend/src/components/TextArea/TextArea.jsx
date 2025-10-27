import React from "react";
import "./TextArea.css";
function TextArea(props) {
  return (
    <div>
      <textarea
        value={props.value || ''}
        className="text-area"
        rows="10"
        cols="50"
        placeholder="your bot will respond here"
        readOnly
      />
    </div>
  );
}

export default TextArea;