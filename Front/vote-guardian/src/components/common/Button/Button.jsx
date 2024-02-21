import './Button.scss'

export default function Button(props) {
    return(
        <>
            <div className={props.buttonType}>
                <p>{props.text}</p>
            </div>
        </>
    )
}