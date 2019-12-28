import React from 'react';

const Star = (props) =>  {
  const checkFirst = props.nth === 1
  return (
        <i style = { checkFirst ? {marginLeft : 5, marginRight : 3} : {marginRight : 3} }
        tabIndex="0"
        aria-checked="false"
        aria-posinset="1"
        aria-setsize="4"
        className="active icon"
        role="radio"
    ></i>
  )
}
export default Star;