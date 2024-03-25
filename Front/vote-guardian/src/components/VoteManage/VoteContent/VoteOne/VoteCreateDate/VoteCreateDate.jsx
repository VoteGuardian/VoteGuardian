import { FaRegCalendarAlt } from "react-icons/fa";

export default function VoteCreateDate(props) {
    return(
        <>
        <div className='vote-period-date'>
            <div className='vote-period-top'>
                <div className='vote-period-title'>
                    <FaRegCalendarAlt size="20"/>
                    <p>생성 일자</p>
                </div>
            </div>
            <div className='vote-period-bottom'>
                <p>{props.createdAt}</p>
            </div>
        </div>
        </>
    )
}