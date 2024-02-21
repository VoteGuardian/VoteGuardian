import './VoteOne.scss';
import { FaCalendarAlt } from "react-icons/fa";
import { FaRegCalendarAlt } from "react-icons/fa";
import { useRecoilValue } from 'recoil';
import { voteListState, voteState, voteType } from "@/recoil/atoms/voteAtoms";

export default function VoteOne() {
    //불러온 (생성한 투표 목록 or 참여 가능한) 투표 목록
    const voteList = useRecoilValue(voteListState);
    //생성한 투표 목록인지 참여 가능한 투표 목록인지 구분할 변수
    const type = useRecoilValue(voteType);
    //투표의 상태(전체, 예정, 진행, 종료)
    const state = useRecoilValue(voteState);

    return(
        <>
        {voteList !== null ? voteList.map((vote) =>
            <div key={vote.id} className='vote-info'>
                <div className='vote-one-top'>
                    {type === 'create' ?
                        vote.state === 1 ?
                        <div className="vote-type before">
                            <p>예정</p>
                        </div>
                        : vote.state === 2 ?
                        <div className="vote-type progress">
                            <p>진행</p>
                        </div>
                        : <div className="vote-type done">
                            <p>종료</p>
                        </div>
                        :
                        state === 1 ?
                        <div className="vote-type before">
                            <p>예정</p>
                        </div>
                        : state === 2 ?
                        <div className="vote-type progress">
                            <p>진행</p>
                        </div>
                        : <div className="vote-type done">
                            <p>종료</p>
                        </div>
                    }
                </div>
                <div className='vote-content'>
                    <div className='vote-title'>
                        <p>{vote.title}</p>
                    </div>
                    <div className='vote-period-date'>
                        <div className='vote-period-top'>
                            <div className='vote-period-title'>
                                <FaCalendarAlt size="20" />
                                <p>투표 기간</p>
                            </div>
                            
                        </div>
                        <div className='vote-period-bottom'>
                            <p className='vote-period-start'>{vote.startAt.substr(0, 16)}</p>
                            <p className='vote-period-end'>~ {vote.finishAt.substr(0, 16)}</p>
                        </div>
                    </div>
                    {type === 'create' &&
                        <div className='vote-period-date'>
                            <div className='vote-period-top'>
                                <div className='vote-period-title'>
                                    <FaRegCalendarAlt size="20"/>
                                    <p>생성 일자</p>
                                </div>
                            </div>
                            <div className='vote-period-bottom'>
                                <p>{vote.createdAt}</p>
                            </div>
                        </div>
                    }  
                </div>
            </div>
        ) : (state == 0 ? 
            <div>
                <h1>투표가 없습니다</h1>
            </div>
        : (state == 1 ?
            <div>
                <h1>예정중인 투표가 없습니다</h1>
            </div>
        : (state == 2 ?
            <div>
                <h1>진행중인 투표가 없습니다</h1>
            </div>
        : <div>
            <h1>종료된 투표가 없습니다</h1>
        </div>)))}
        </>
    )
}