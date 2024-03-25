import { FaCalendarAlt } from "react-icons/fa";

export default function VoteDate(props) {
    return (
        <>
        <div className='vote-period-date'>
            <div className='vote-period-top'>
                <div className='vote-period-title'>
                    <FaCalendarAlt size="20" />
                    <p>투표 기간</p>
                </div>
            </div>
            <div className='vote-period-bottom'>
                <p className='vote-period-start'>{props.startAt}</p>
                <p className='vote-period-end'>~ {props.finishAt}</p>
            </div>
        </div>
        </>
    )
}