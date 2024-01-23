import './VoteOne.scss';
import { FaCalendarAlt } from "react-icons/fa";
import { FaRegCalendarAlt } from "react-icons/fa";

export default function VoteOne({title, startAt, finishAt, createdAt, state}) {
    const startDate = startAt.substr(0, 16);
    const endDate = finishAt.substr(0, 16);
    const createdDate = createdAt.substr(0, 16);

    return(
        <div className="vote-info">
            <div className='vote-one-top'>
                {/* 1 : 예정, 2 : 진행 , 3: 종료 */}
                {state == 1 ? 
                    <div className="vote-type before">
                        <p>예정</p>
                    </div>
                : (state == 2) ?
                    <div className="vote-type progress">
                        <p>진행</p>
                    </div>
                : <div className="vote-type done">
                    <p>종료</p>
                    </div>}
            </div>
            <div className='vote-content'>
                <div className='vote-title'>
                    <p>{title}</p>
                </div>
                <div className='vote-period-date'>
                    <div className='vote-period-top'>
                        <div className='vote-period-title'>
                            <FaCalendarAlt size="20" />
                            <p>투표 기간</p>
                        </div>
                        <p className='vote-period-start'>{startDate} </p>
                    </div>
                    <div className='vote-period-bottom'>
                        <p className='vote-period-end'>- {endDate}</p>
                    </div>
                </div>
                <div className='vote-created-date'>
                    <div className='vote-created-left'>
                        <FaRegCalendarAlt size="20"/>
                        <p className='vote-create'>생성 일자</p>
                    </div>
                    <div className='vote-created-right'>
                        <p>{createdDate}</p>
                    </div>
                </div>
            </div>
        </div>
    )
}