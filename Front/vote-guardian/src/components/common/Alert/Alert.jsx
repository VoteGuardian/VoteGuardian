import './Alert.scss'

export default function Alert(props) {
    return (
        <>
            <div className={`alert ${props.type}`}>
                <p>{props.text}</p>
            </div>
        </> 
    )
}