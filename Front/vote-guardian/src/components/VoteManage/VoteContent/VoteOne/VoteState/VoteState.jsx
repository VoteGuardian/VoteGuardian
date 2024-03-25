import './VoteState.scss';

export default function VoteState(props) {
    return (
        <>
        <div className="vote-one-top">
            {props.state === 1 ?
            <div className="vote-type before">
                <p>예정</p>
            </div>
            : props.state === 2 ?
            <div className="vote-type progress">
                <p>진행</p>
            </div>
            :<div className="vote-type done">
                <p>종료</p>
            </div>
            }
        </div>
        </>
    )
}