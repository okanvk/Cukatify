import React from 'react'

const NotFound = () => {
    return (
        <div class="ui error message center">
        <i class="close icon"></i>
            <div className="header">
                There were some errors
            </div>
            <ul className="list">
                <li>This isn't the web page you are looking for.</li>
            </ul>
        </div>
    )
}

export default NotFound;